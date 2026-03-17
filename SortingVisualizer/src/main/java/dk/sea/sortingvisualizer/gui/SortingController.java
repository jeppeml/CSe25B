package dk.sea.sortingvisualizer.gui;

import dk.sea.sortingvisualizer.be.*;
import dk.sea.sortingvisualizer.bll.*;
import dk.sea.sortingvisualizer.bll.algorithms.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;

public class SortingController implements Initializable {

    @FXML private ComboBox<String> cmbAlgorithm;
    @FXML private ComboBox<String> cmbEntity;
    @FXML private Spinner<Integer> spnSize;
    @FXML private Button btnPlayPause;
    @FXML private Slider sldSpeed;
    @FXML private StackPane visualizationPane;
    @FXML private Label lblComparisons, lblSwaps, lblRemoves, lblStep, lblCapWarning;

    private final List<ISortingAlgorithm> algorithms = List.of(
        new BubbleSort(), new SelectionSort(), new InsertionSort(),
        new MergeSort(), new QuickSort(),
        new StalinSort(), new BogoSort(), new StupidSort()
    );

    private record EntityOption(String name, Function<Integer, ArrayList<ISortable>> generator) {
        @Override public String toString() { return name; }
    }

    private final List<EntityOption> entities = List.of(
        new EntityOption("Number", NumberItem::generateRandom),
        new EntityOption("Dog", Dog::generateRandom),
        new EntityOption("Person", Person::generateRandom)
    );

    private SortingVisualization visualization;
    private List<SortStep> steps;
    private List<ISortable> originalData;
    private int currentStepIndex = -1;
    private Timeline playTimeline;
    private boolean isPlaying = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        visualization = new SortingVisualization();
        visualizationPane.getChildren().add(visualization);
        // Bind canvas size to pane layout bounds to avoid feedback loop
        visualizationPane.layoutBoundsProperty().addListener((obs, old, bounds) -> {
            visualization.setWidth(bounds.getWidth());
            visualization.setHeight(bounds.getHeight());
        });

        for (ISortingAlgorithm algo : algorithms) {
            cmbAlgorithm.getItems().add(algo.getName());
        }
        cmbAlgorithm.getSelectionModel().selectFirst();

        for (EntityOption entity : entities) {
            cmbEntity.getItems().add(entity.name());
        }
        cmbEntity.getSelectionModel().selectFirst();

        spnSize.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 100, 15));
        spnSize.setEditable(true);

        cmbAlgorithm.valueProperty().addListener((obs, old, newVal) -> {
            if ("Bogo Sort".equals(newVal)) {
                SpinnerValueFactory.IntegerSpinnerValueFactory factory =
                    (SpinnerValueFactory.IntegerSpinnerValueFactory) spnSize.getValueFactory();
                if (factory.getValue() > 7) {
                    factory.setValue(7);
                }
                factory.setMax(7);
            } else {
                SpinnerValueFactory.IntegerSpinnerValueFactory factory =
                    (SpinnerValueFactory.IntegerSpinnerValueFactory) spnSize.getValueFactory();
                factory.setMax(100);
            }
        });

        sldSpeed.valueProperty().addListener((obs, old, newVal) -> {
            if (isPlaying) {
                stopPlayback();
                startPlayback();
            }
        });
    }

    @FXML
    private void onGenerate() {
        stopPlayback();
        int size = spnSize.getValue();
        int entityIdx = cmbEntity.getSelectionModel().getSelectedIndex();
        originalData = entities.get(entityIdx).generator().apply(size);
        runSort();
    }

    @FXML
    private void onReset() {
        stopPlayback();
        if (originalData == null) return;
        runSort();
    }

    private void runSort() {
        int algoIdx = cmbAlgorithm.getSelectionModel().getSelectedIndex();
        ISortingAlgorithm algorithm = algorithms.get(algoIdx);
        SortingContext context = new SortingContext(new ArrayList<>(originalData));

        try {
            algorithm.sort(context);
        } catch (MaxStepsReachedException e) {
            // Cap reached — handled below
        }

        steps = context.getSteps();
        currentStepIndex = -1;

        visualization.showData(new ArrayList<>(originalData), null);
        updateStats(0, 0, 0);

        if (context.isCapReached()) {
            lblCapWarning.setText("Step cap reached! (" + steps.size() + " steps)");
        } else {
            lblCapWarning.setText("");
        }
    }

    @FXML
    private void onStepForward() {
        if (steps == null || steps.isEmpty()) return;
        if (currentStepIndex < steps.size() - 1) {
            currentStepIndex++;
            showCurrentStep();
        }
    }

    @FXML
    private void onStepBack() {
        if (steps == null || steps.isEmpty()) return;
        if (currentStepIndex > 0) {
            currentStepIndex--;
            showCurrentStep();
        } else if (currentStepIndex == 0) {
            currentStepIndex = -1;
            visualization.showData(new ArrayList<>(originalData), null);
            updateStats(0, 0, 0);
        }
    }

    @FXML
    private void onPlayPause() {
        if (isPlaying) {
            stopPlayback();
        } else {
            startPlayback();
        }
    }

    private void startPlayback() {
        if (steps == null || steps.isEmpty()) return;
        if (currentStepIndex >= steps.size() - 1) return;
        isPlaying = true;
        btnPlayPause.setText("Pause");
        double delayMs = sldSpeed.getValue();
        playTimeline = new Timeline(new KeyFrame(
            Duration.millis(delayMs), e -> {
                if (currentStepIndex < steps.size() - 1) {
                    currentStepIndex++;
                    showCurrentStep();
                } else {
                    stopPlayback();
                }
            }
        ));
        playTimeline.setCycleCount(Timeline.INDEFINITE);
        playTimeline.play();
    }

    private void stopPlayback() {
        isPlaying = false;
        btnPlayPause.setText("Play");
        if (playTimeline != null) {
            playTimeline.stop();
            playTimeline = null;
        }
    }

    private void showCurrentStep() {
        if (currentStepIndex < 0 || currentStepIndex >= steps.size()) return;
        SortStep step = steps.get(currentStepIndex);
        visualization.showData(step.snapshot(), step);
        updateStatsFromSteps();
    }

    private void updateStatsFromSteps() {
        int comparisons = 0, swapCount = 0, removeCount = 0;
        for (int i = 0; i <= currentStepIndex; i++) {
            switch (steps.get(i).type()) {
                case COMPARE -> comparisons++;
                case SWAP -> swapCount++;
                case REMOVE -> removeCount++;
                default -> {}
            }
        }
        updateStats(comparisons, swapCount, removeCount);
    }

    private void updateStats(int comparisons, int swapCount, int removeCount) {
        lblComparisons.setText("Comparisons: " + comparisons);
        lblSwaps.setText("Swaps: " + swapCount);
        lblRemoves.setText("Removes: " + removeCount);
        int total = (steps != null) ? steps.size() : 0;
        lblStep.setText("Step: " + (currentStepIndex + 1) + "/" + total);
    }
}
