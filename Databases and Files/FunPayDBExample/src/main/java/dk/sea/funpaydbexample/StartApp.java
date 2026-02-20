package dk.sea.funpaydbexample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gui/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("FunPay");
        stage.setScene(scene);
        stage.show();
    }
}
