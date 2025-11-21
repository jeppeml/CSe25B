package dk.easv.database3layered.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import dk.easv.database3layered.be.Dog;
import dk.easv.database3layered.be.DogOwner;
import dk.easv.database3layered.exceptions.DogException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DogsDAO {
    ConnectionManager conMan = new ConnectionManager();

    public void deleteDog(Dog dog) throws DogException {
        try(Connection con = conMan.getConnection()){
            String sql = "DELETE FROM Dogs WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, dog.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DogException("Could not delete the dog",e);
        }
    }

    public List<Dog> getAllDogs() throws DogException {
        List<Dog> dogs = new ArrayList<>();
        try(Connection con = conMan.getConnection())
        {
            String sql = "SELECT * FROM Dogs";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id          = rs.getInt("id");
                String name     = rs.getString("name");
                int birthYear = rs.getInt("byear");
                int ownerId = rs.getInt("owner_id");

                Dog dog = new Dog(id, name, birthYear, ownerId);
                dogs.add(dog);

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DogException("Problem with DB connection", e);
        }
        return dogs;

    }
}
