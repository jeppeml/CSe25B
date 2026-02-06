package dk.easv.delete_this_stuff;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    int colorCurrent =0;
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customButton.fxml"));
        //Parent root = fxmlLoader.load();
        int howManyButtons = 200;
        Button[] btns = new Button[howManyButtons];
        FlowPane root = new FlowPane();
        Region n = new Region();
        for (int i = 0; i < howManyButtons; i++) {
            Color color = Color.hsb(360f*(i*1f/howManyButtons), 1,1);
            Button button = new Button("Hello");
            int red   = (int) (255 * color.getRed());
            int green = (int) (255 * color.getGreen());
            int blue  = (int) (255 * color.getBlue());
            String cssStyle = "-fx-background-color: rgb(" + red + ", " + green + ", " + blue + ")";
            button.setStyle(cssStyle);
            btns[i] = button;
            button.setOnAction(event -> {

                Alert alert = new Alert(Alert.AlertType.ERROR,"Are you stupids?");
                alert.getDialogPane().setStyle(cssStyle);
                alert.show();
            });
            root.getChildren().add(button);
        }
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                colorChange(btns, howManyButtons);
            }
        };
        timer.start();

        Scene scene = new Scene(root, 320, 240);
        stage.setScene(scene);
        stage.show();
    }

    private void colorChange(Button[] btns, int howManyButtons){
        colorCurrent+=5;
        for (int i = 0; i < howManyButtons; i++) {
            Color color = Color.hsb(((360f*(i*1f/howManyButtons))+colorCurrent)%360, 1,1);
            int red   = ((int) (255 * color.getRed()));
            int green = ((int) (255 * color.getGreen()));
            int blue  = ((int) (255 * color.getBlue()));
            String cssStyle = "-fx-background-color: rgb(" + red + ", " + green + ", " + blue + ")";
            btns[i].setStyle(cssStyle);
        }

    }
}
