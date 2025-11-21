package dk.easv.database3layered.gui;

import dk.easv.database3layered.be.Dog;
import dk.easv.database3layered.be.DogOwner;
import dk.easv.database3layered.bll.Logic;
import dk.easv.database3layered.exceptions.DogException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.List;

public class HelloController {
    @FXML
    private TextArea txtOutput;

    @FXML
    private Label welcomeText;

    private Logic logic = new Logic();

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Fetching data");
        txtOutput.setText("some data");

        // Get all dog owners and write them to screen
        List<DogOwner> owners = null;
        List<Dog> dogs = null;
        try {
            owners = logic.getAllOwners();
            txtOutput.clear();
            for (DogOwner owner : owners) {
                txtOutput.appendText(owner.toString() + "\n");
                dogs = owner.getDogs();
                txtOutput.appendText("\nDOGS:\n");
                for (Dog dog : dogs) {
                    txtOutput.appendText(dog.toString() + "\n");
                }
                txtOutput.appendText("\n\n");
            }


        } catch (DogException e) {
            welcomeText.setText(e.getMessage());
            txtOutput.clear();
            txtOutput.appendText("Guru meditation: "+e.toString());
        }

    }
}
