package dk.easv.firstjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    public Button btnTop;

    @FXML
    private Label welcomeText;

    @FXML
    private TextField myTextField;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("CSe2025 is Cool!");
    }

    public void jeppeButtonClick(){
        System.out.println("You clicked the button! Weee!");
        welcomeText.setText("You clicked the better button! Wee!");
        String someText = myTextField.getText();
        btnTop.setText(someText);
    }
}
