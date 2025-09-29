package dk.easv.datamanagementmvc;

import java.util.ArrayList;

public class DataModel {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        int i; // i == 0
        Integer i2; // i2 == null
        double d; // d == 0.0
        Double d2; // d2 == null
        d2 = 5.0;
        boolean bool; // bool == false
        ArrayList a; // a == null

        DataWindowController dwc = new DataWindowController();
        dwc.toString();
        return name;
    }
}
