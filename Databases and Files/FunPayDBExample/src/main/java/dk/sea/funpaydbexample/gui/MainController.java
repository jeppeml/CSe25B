package dk.sea.funpaydbexample.gui;

import dk.sea.funpaydbexample.FunPayException;
import dk.sea.funpaydbexample.be.User;
import dk.sea.funpaydbexample.bll.Logic;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML private Label lblBalance;
    @FXML private Label lblName;
    @FXML private FlowPane flowProducts;
    private final Model model = new Model();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try{
            model.loadFirstUser();
        }
        catch (FunPayException e){
            showError("Error in db", "No users in db", e.getMessage());
        }
        lblName.textProperty().bind(model.getFirstUser().nameProperty());
        lblBalance.textProperty().bind(model.getFirstUser().balanceProperty().asString());
    }

    public void loadDB(ActionEvent actionEvent) {
        try {
            model.loadFirstUser();
        } catch (FunPayException e) {
            showError("Error in db", "Error in db", e.getMessage());
        }
    }
    private void showError(String title, String header, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
