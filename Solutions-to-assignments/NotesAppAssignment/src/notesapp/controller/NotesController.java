package notesapp.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller class for NotesApp.
 */
public class NotesController implements Initializable {
    private static final String CONFIG = "config.txt";

    private boolean useModern = true;

    @FXML private TextArea textArea;
    @FXML private Label statusLabel;

    private File currentFile;  // Tracks the currently open/saved file

    /** Called when user clicks "New". */
    @FXML
    private void handleNew() {
        textArea.clear();
        currentFile = null;
        statusLabel.setText("New note");
    }

    private void openFile(File file) {
        currentFile = file;
        try {
            if (useModern) {
                // use java.nio
            /* List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            StringBuilder sb = new StringBuilder();
            for(String line : lines) {
                sb.append(line).append("\n");
            }
            textArea.setText(sb.toString());*/

                textArea.setText(Files.readString(file.toPath()));

            } else {
                try(BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                    List<String> lines = reader.lines().toList();
                    StringBuilder sb = new StringBuilder();
                    for (String line : lines) {
                        sb.append(line).append("\n");
                        //textArea.appendText(line + "\n");
                    }
                    textArea.setText(sb.toString());
                }
            }
        }
        catch (IOException e) {
            statusLabel.setText("Error: Could not read file");
            e.printStackTrace();
            return;
        }

        try {
            saveLastOpenedFile(currentFile);
            statusLabel.setText("Opened: " + file.getName());
        } catch (IOException e) {
            statusLabel.setText("Error: Could save config file");
            e.printStackTrace();
        }
    }

    /** Called when user clicks "Open". */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        if (currentFile != null)
            fileChooser.setInitialDirectory(currentFile.getParentFile());
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(new Stage());
        if (file == null) return;

        openFile(file);
    }

    /** Called when user clicks "Save". */
    @FXML
    private void handleSave() {
        if (currentFile == null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            if (currentFile != null)
                fileChooser.setInitialDirectory(currentFile.getParentFile());
            fileChooser.setTitle("Save File");
            currentFile = fileChooser.showSaveDialog(new Stage());
        }
        if (currentFile == null) return;
        saveFile(currentFile);
    }
    private void saveFile(File file) {
        String content = textArea.getText();
        try {
            if (useModern) {
                Files.writeString(file.toPath(), content);
            } else {
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(content);
                }
            }
        }
        catch (IOException e) {
            statusLabel.setText("Error: Could not write file");
            e.printStackTrace();
            return;
        }

        try {
            saveLastOpenedFile(currentFile);
            statusLabel.setText("Saved: " + currentFile.getName());
        } catch (IOException e) {
            statusLabel.setText("Error: Could not set config file");
            e.printStackTrace();
        }
    }
    private void saveLastOpenedFile(File file) throws IOException {
        Files.writeString(Paths.get(CONFIG), file.getAbsolutePath(),
                StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Files.exists(Paths.get(CONFIG))){
            try {
                String lastOpened = Files.readString(Paths.get(CONFIG));
                openFile(new File(lastOpened));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Timeline autosave = new Timeline(
                new KeyFrame(Duration.seconds(15), event -> autoSave())
        );
        autosave.setCycleCount(Timeline.INDEFINITE);
        autosave.play();
    }

    private void autoSave() {
        if(currentFile != null){
            saveFile(currentFile);
            statusLabel.setText("Autosaved: " + currentFile.getName());
        }
    }
}