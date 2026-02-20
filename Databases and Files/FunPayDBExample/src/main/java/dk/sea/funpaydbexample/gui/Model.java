package dk.sea.funpaydbexample.gui;

import dk.sea.funpaydbexample.FunPayException;
import dk.sea.funpaydbexample.be.User;
import dk.sea.funpaydbexample.bll.Logic;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

public class Model {
    private final SimpleObjectProperty<User> user = new SimpleObjectProperty<>();
    private final Logic bll = new Logic();

    public Model() {
        user.set(new User(1,"",0));
    }

    public void loadFirstUser() throws FunPayException {
        User u = bll.getFirstUser();
        user.get().setName(u.getName());
        user.get().setBalance(u.getBalance());
    }

    public SimpleObjectProperty<User> userProperty() {
        return user;
    }

    public User getFirstUser() {
        return user.get();
    }
}
