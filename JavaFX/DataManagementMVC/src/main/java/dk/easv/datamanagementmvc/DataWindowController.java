package dk.easv.datamanagementmvc;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DataWindowController {
    @FXML
    private Label txtName;

    private DataModel dataModel;

    public void setModel(DataModel dataModel) {
        this.dataModel = dataModel;
        txtName.setText(dataModel.getName());
    }
}
