Solution to the NotesAppAssignement

Video on YouTube [https://youtu.be/MfHkPo2vSOo](https://youtu.be/MfHkPo2vSOo)

The Assignment (copy may be outdated):
**Goal:**  
Create a simple JavaFX application that lets users write, open, and save notes.  
Through each step, you’ll train both file handling (classic and modern APIs) and GUI interaction using the MVC pattern (FXML + Controller).

Try saving each step of the way in git so you can always revert back.

Start by creating a new project in IntelliJ. This time, just choose the **Java** project, without JavaFX. Do remember to use **Liberica Full** as the SDK.

Create the following structure:

---

## 📁 Project Structure

```
src/
 └── notesapp/
      ├── Main.java
      ├── controller/
      │    └── NotesController.java
      └── view/
           └── notes_view.fxml
```

You can create folders by Right Clicking in the project file area, and choosing "New... Package"

The structure looks different, as this doesn't use Maven like you are used to. (No resources folder). We don't need it for this exercise.

Copy paste the contents below into the files

---

## ⚙️ Step 1 - Base Application Setup

Try filling in all of the code below. Remember to check that the program is running.

### **Main.java**
```java
package notesapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point of the NotesApp JavaFX application.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/notes_view.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("NotesApp");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

### **notes_view.fxml**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<VBox xmlns="http://javafx.com/javafx"
      xmlns:fx="http://javafx.com/fxml"
      fx:controller="notesapp.controller.NotesController"
      alignment="CENTER">

    <HBox spacing="10">
        <Button text="New" onAction="#handleNew"/>
        <Button text="Open" onAction="#handleOpen"/>
        <Button text="Save" onAction="#handleSave"/>
    </HBox>

    <TextArea fx:id="textArea" prefRowCount="15" prefColumnCount="40"
              promptText="Write your notes here..."/>
    <Label fx:id="statusLabel" text="Ready" />
</VBox>
```

### **NotesController.java**
```java
package notesapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/**
 * Controller class for NotesApp.
 */
public class NotesController {

    private boolean useModern = false;

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

    /** Called when user clicks "Open". */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(new Stage());
        if (file == null) return;
        currentFile = file;

        // --- Training area #1 ---
        // OPTION A: Using BufferedReader / FileReader (classic I/O)
        //   1. Create a BufferedReader from the chosen file.
        //   2. Read line by line and append to a StringBuilder.
        //   3. Update textArea with the file content.
        //   4. Close the reader safely (try-with-resources).
        //
        // OPTION B: Using java.nio.file.Files (modern approach)
        //   1. Use Files.readString(file.toPath(), StandardCharsets.UTF_8)
        //      to read the entire content in one call.
        //   2. Set the result into textArea.

        statusLabel.setText("Opened: " + file.getName());
    }

    /** Called when user clicks "Save". */
    @FXML
    private void handleSave() {
        if (currentFile == null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            currentFile = fileChooser.showSaveDialog(new Stage());
        }
        if (currentFile == null) return;

        String content = textArea.getText();

        // --- Training area #2 ---
        // OPTION A: Using BufferedWriter / FileWriter (classic I/O)
        //   1. Create a BufferedWriter for currentFile.
        //   2. Write the content from textArea.
        //   3. Close the writer with try-with-resources.
        //
        // OPTION B: Using java.nio.file.Files (modern approach)
        //   1. Use Files.writeString(currentFile.toPath(), content, StandardCharsets.UTF_8)
        //      to save in a single line.

        statusLabel.setText("Saved: " + currentFile.getName());
    }
}
```

### ✅ What to do?

Try opening a file, saving a file and creating a new file. It doesn't work.

You need to implement the reading and writing of the files in the "Training" areas.

Use the `useModern` varaiable to decide if the program should use the older `BufferedReader/BufferedWriter` or thee newer java.nio and `Files`

You don't need to be able to choose between them from the gui, just from the variable in the code.

#### Bonus Challenge (OPTIONAL: skip if first part was hard)
If you feel like a challenge. Try thinking about if you could make the architecture better, like using inheritance or composition/aggregation to make the code more flexible? (lower coupling and higher cohesion?)

---

## 🧠 Step 2 - Persist Last Opened File

Let's imagine we want to remember the last opened file. We want to store this into a file: `config.txt` and open it automatically on startup.

- Read `config.txt` (use `Files.readString()`).
- If the file doesn't exist, create it and then read it
- After saving or opening a new file, update the current last opened file in `config.txt` with `Files.writeString()`.

**Hints:**
```java
private static final String CONFIG = "config.txt";

@FXML
public void initialize() {
    // Check if config.txt exists → read path → open that file automatically.
    // Otherwise create it
    // Remember to make your controller implement the interface `Initializable`
}

private void updateConfig() {
    // Write currentFile.getAbsolutePath() to config.txt using java.nio.file.Files

}
```

#### Bonus Challenge (OPTIONAL: skip if first part was hard)
Think about how you can later use the config file for other things and think about how to structure the file.

Use a INI approach like
``` 
[files]
lastopened = 'c:\users\PickleRick\Desktop\QuantumFluxCapacitor\calculations.tex'

[style]
bgcolor = 'blue'
fontsize = 10
```

or maybe YAML

```
files:
  lastopened: "c:\\users\\PickleRick\\Desktop\\QuantumFluxCapacitor\\calculations.tex"

style:
  bgcolor: "blue"
  fontsize: 10
```

or JSON

```
{
  "files": {
    "lastopened": "c:\\users\\PickleRick\\Desktop\\QuantumFluxCapacitor\\calculations.tex"
  },
  "style": {
    "bgcolor": "blue",
    "fontsize": 10
  }
}
```

Think about how you might choose custom color styles etc. and make it affect the CSS?

---

## 🧩 Step 3 - Error Handling & Status Messages

**Goal:** Improve UX and reliability.

- Surround file operations with try/catch.
- Show readable messages in `statusLabel` (e.g., “Error: Could not open file”).
- Optionally, show `Alert` dialogs for errors.

**Hints:**
```java
try {
    // File operation
    statusLabel.setText("Opened successfully");
} catch (IOException e) {
    statusLabel.setText("Error: " + e.getMessage());
    new Alert(Alert.AlertType.ERROR, "Could not read file.").showAndWait();
}
```

#### Bonus Challenge (skip if first part was hard)
Try looking up the UX design pattern **Toast** or **Snack bar** could you implement that? Maybe there is a library that can help, or you just like the challenge of using animations in JavaFX?


---

## 🧩 Step 4 - Autosave Feature

Now we want to save the opened file automagically every 60 seconds.

- Try using `javafx.animation.Timeline`.
- Every interval: if `currentFile != null`, write the current text content.

**Hints:**
```java
Timeline autosave = new Timeline(
    new KeyFrame(Duration.seconds(60), e -> autoSave())
);
autosave.setCycleCount(Animation.INDEFINITE);
autosave.play();

private void autoSave() {
    if (currentFile != null) {
        // SAVE FILE HERE...
        statusLabel.setText("Autosaved " + currentFile.getName());
    }
}
```

---

## 🧩 Step 5 - Enhance FileChooser Experience

The FileChooser is a bit annoying right now. Let's improve the usability.

- Set initial directory to the last used folder.
- Add file filters (e.g., `Text Files (*.txt)`).
- Remember directory in `config.txt` if desired.

**Hints:**
```java
FileChooser fileChooser = new FileChooser();
fileChooser.getExtensionFilters().add(
    new FileChooser.ExtensionFilter("Text Files", "*.txt")
);
if (currentFile != null)
    fileChooser.setInitialDirectory(currentFile.getParentFile());
```
Try also implementing a "Save as..." button.

---

## Optional: Multi-Note Storage with JSON

Try storing multiple notes in one JSON file. Have a list of notes in a ListView, on the left of the TextArea, so that the user can open different chapters. 

Alternatively, load the entire file, but use the ListView to jump to certain locations in the text (this is hard)

- Use a `List<Note>` where each note has a `title` and `content`.
- Convert to/from JSON using a simple manual format or built-in tools.
- Show note titles in a `ListView`; when selected, show content in `TextArea`.

**Hints:**
```java
// For basic manual JSON-like saving without libraries:
Files.writeString(file.toPath(), "{ \"title\": \"" + title + "\", \"content\": \"" + text + "\" }");
```
