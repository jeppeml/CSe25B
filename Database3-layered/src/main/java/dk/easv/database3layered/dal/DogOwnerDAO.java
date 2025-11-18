package dk.easv.database3layered.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dk.easv.database3layered.be.DogOwner;
import dk.easv.database3layered.exceptions.DogException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DogOwnerDAO {
    ConnectionManager conMan = new ConnectionManager();

    public List<DogOwner> getAllOwners() throws DogException {
        List<DogOwner> dogOwners = new ArrayList<>();
        try(Connection con = conMan.getConnection())
        {
            String sql = "SELECT * FROM DogOwners ORDE BY name";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id          = rs.getInt("id");
                String name     = rs.getString("name");
                DogOwner dogOwner = new DogOwner(id, name);
                dogOwners.add(dogOwner);
            }
        }
        catch (SQLException e) {
            throw new DogException("Problem with DB connection", e);
        }
        return dogOwners;

    }
}
