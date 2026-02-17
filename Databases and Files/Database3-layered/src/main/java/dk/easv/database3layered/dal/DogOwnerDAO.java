package dk.easv.database3layered.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dk.easv.database3layered.be.Dog;
import dk.easv.database3layered.be.DogOwner;
import dk.easv.database3layered.exceptions.DogException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DogOwnerDAO implements IDogOwnerDAO {
    ConnectionManager conMan = new ConnectionManager();

    public List<DogOwner> getAllOwners() throws DogException {
        List<DogOwner> dogOwners = new ArrayList<>();
        try(Connection con = conMan.getConnection())
        {
            String sql = "SELECT DogOwners.id, DogOwners.name, Dogs.id AS dog_id, Dogs.name AS dog_name, Dogs.byear\n" +
                    "FROM Dogs\n" +
                    "    JOIN dbo.DogOwners ON DogOwners.id = Dogs.owner_id";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id          = rs.getInt("id");
                String name     = rs.getString("name");
                int dogId      = rs.getInt("dog_id");
                String dogName    = rs.getString("dog_name");
                int birthYear =  rs.getInt("byear");

                //System.out.println(dogId + " " + name + " " + dogName + " " + birthYear);
                Dog dog = new Dog(dogId, dogName, birthYear);
                DogOwner dogOwner = new DogOwner(id, name);
                boolean foundOwner = false;
                for(DogOwner dogOwner2 : dogOwners){
                    if(id==dogOwner2.getId()){
                        dogOwner2.getDogs().add(dog);
                        foundOwner = true;
                        break;
                    }
                }
                if(!foundOwner){
                    dogOwners.add(dogOwner);
                    dogOwner.getDogs().add(dog);
                }

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DogException("Problem with DB connection DogOwner", e);

        }
        return dogOwners;

    }
}
