package dk.sea.funpaydbexample.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import dk.sea.funpaydbexample.FunPayException;
import dk.sea.funpaydbexample.be.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    ConnectionManager conMan =  new ConnectionManager();
    public User getFirstUser() throws FunPayException {
        try (Connection con = conMan.getConnection()){
            String sql = "SELECT * FROM users";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float balance = rs.getFloat("balance");
                User u = new User(id, name, balance);
                return u;
            }

            throw new FunPayException("User not found");

        } catch (SQLException e) {
            throw new FunPayException("Problems connecting to DB", e);
        }

    }
}
