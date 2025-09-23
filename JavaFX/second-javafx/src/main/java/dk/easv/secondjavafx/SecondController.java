package dk.easv.secondjavafx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SecondController {
    @FXML
    private Label lblInfo;
    private String info;
    private HelloController controller;

    public void setParentController(HelloController controller) {
        this.controller = controller;
    }

    public void setInfo(String info) {
        this.info = info;
        lblInfo.setText(info);
    }

    public void saveAndCloseClick(ActionEvent actionEvent) {
        controller.sendInfo("IMPORTENT INFO");

    }
}
