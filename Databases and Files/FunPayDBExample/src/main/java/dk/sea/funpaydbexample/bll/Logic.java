package dk.sea.funpaydbexample.bll;

import dk.sea.funpaydbexample.FunPayException;
import dk.sea.funpaydbexample.be.User;
import dk.sea.funpaydbexample.dal.UserDAO;

public class Logic {
    UserDAO userDAO = new UserDAO();
    public User getFirstUser() throws FunPayException {
        return userDAO.getFirstUser();
    }
}
