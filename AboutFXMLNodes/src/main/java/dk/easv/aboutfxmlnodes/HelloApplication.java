package dk.easv.aboutfxmlnodes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent rootElement = fxmlLoader.load();


        printNode(rootElement, 0);
        Scene scene = new Scene(rootElement, 320, 240);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void printNode(Node node, int lvl) {
        System.out.println(node.toString());
        Parent parent = (Parent) node;
        for(Node child: parent.getChildrenUnmodifiable()){
            for (int i = 0; i < lvl; i++) {
                System.out.print("L->");
            }
            printNode(child, lvl+1);
        }

    }
}
