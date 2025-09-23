package dk.easv.secondjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SecondController {
    @FXML
    private Label lblInfo;
    private String info;

    public void setInfo(String info) {
        this.info = info;
        lblInfo.setText(info);
    }
}
