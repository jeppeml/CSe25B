package dk.easv.exceptionexamples.gui;

import dk.easv.exceptionexamples.dal.TextDAO;
import dk.easv.exceptionexamples.exceptions.MyOwnException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HelloController {
    @FXML
    private TextArea txtContents;

    @FXML
    private Label welcomeText;

    private TextDAO textDAO = new TextDAO();

    @FXML
    protected void onHelloButtonClick() {
        String fileName = "sometext.txt";
        try {
            txtContents.setText(textDAO.readFile(fileName));
        } catch (MyOwnException e) {
            welcomeText.setText(e.getMessage());
            e.printStackTrace();
        }
    }
}
