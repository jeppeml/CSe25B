package dk.sea.sortingvisualizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;

public class SortingVisualizerApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/sorting-view.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Sorting Algorithm Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();

        Arrays.sort()
    }

    public static void main(String[] args) {
        launch(args);
    }
}
