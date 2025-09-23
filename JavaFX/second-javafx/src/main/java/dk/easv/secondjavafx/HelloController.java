package dk.easv.secondjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        System.out.println("Hello");

        double rand = Math.random();
        if (rand < 0.5) {
            System.out.println("Hello low number");
        }
        else if (rand < 0.7) {
            System.out.println("Hello middle number");
        }
        else if (rand < 0.9) {
            System.out.println("Hello high number");
        }

    }

    public void openWindowClick(ActionEvent actionEvent) throws IOException {
        System.out.println("Should open window?");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SecondWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String info = "This is the info I need";
        SecondController sc = fxmlLoader.getController();
        sc.setInfo(info);
        Stage stage = new Stage();
        stage.setTitle("RHELLOOOO!");
        stage.setScene(scene);
        stage.show();
    }
}
