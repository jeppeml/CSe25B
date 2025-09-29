package dk.easv.datamanagementmvc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;

public class HelloController {
    @FXML
    private TextField txtName;

    @FXML
    private Label welcomeText;

    private DataModel dataModel;

    public HelloController(){
        dataModel = new DataModel();

    }

    @FXML
    protected void onHelloButtonClick() throws IOException {
        welcomeText.setText("Welcome to JavaFX Application!");
        System.out.println(txtName.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("data-window.fxml"));
        dataModel.setName(txtName.getText());
        //fxmlLoader.getController().setName(txtName.getText());

        Scene scene = new Scene(fxmlLoader.load());
        DataWindowController controller = fxmlLoader.getController();
        controller.setModel(dataModel);
        Stage stage = new Stage(); // new window
        stage.setTitle("This is your data!");
        stage.setScene(scene);
        stage.show();
    }
}
