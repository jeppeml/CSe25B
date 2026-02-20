package dk.sea.funpaydbexample.be;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
    private int id;
    private final SimpleStringProperty name =  new SimpleStringProperty();
    private final SimpleFloatProperty balance = new SimpleFloatProperty();

    public User(int id, String name, float balance) {
        this.id = id;
        setName(name);
        setBalance(balance);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    public SimpleStringProperty nameProperty() {
        return name;
    }

    public float getBalance() {
        return balance.get();
    }

    public void setBalance(float balance) {
        this.balance.set(balance);
    }

    public SimpleFloatProperty balanceProperty() {
        return balance;
    }
}
