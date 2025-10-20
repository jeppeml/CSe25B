package dk.easv.datamanagementmvc;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.List;

import java.util.ArrayList;

public class DataWindowController {
    @FXML
    private Label txtName;
    List l;

    private DataModel dataModel;

    private int top = 4;
    private int bottom = 7;
    private int left = 5;
    private int right = -2;

    private int[] ints =  new int[4];
    public void setModel(DataModel dataModel) {
        this.dataModel = dataModel;

        ints[0] = 4;
        ints[1] = 7;
        ints[2] = 5;
        ints[3] = -2;
        // ints[4] = -2; // error out of bounds

        for (int i = 0; i < ints.length; i++) {
            ints[i] = 56;
        }

        for (int i: ints) {
            i = 56;
        }


        // for i
        // for(int i = 0; i < 50; i++)

        // foreach
        // for (Wombat w : wombatList) // needs iterator

        // while
        // while(condition)
        // while(input < 4)
        // while(true) // runs forever

        // do while
        // do{ code }while(condition)

        // repeat the name 50 times in the label
        /*String nameTimes50 = "";
        for(int i = 0; i < 50; i++) {
            nameTimes50 += dataModel.getName() + " ";
        }*/
        // Try with while
        String nameTimes50 = "";
        int count = 0;
        while(count<50){
            count++;
            nameTimes50 += dataModel.getName() + " ";
        }

        ArrayList<DataModel> models = new ArrayList<>();
        models.add(new DataModel());
        models.add(new DataModel());
        models.add(new DataModel());
        models.add(new DataModel());

        for(DataModel dm : models){
            // do on dm model
        }

        txtName.setText(nameTimes50);
    }
}
