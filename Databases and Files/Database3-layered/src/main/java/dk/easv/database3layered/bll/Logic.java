package dk.easv.database3layered.bll;

import dk.easv.database3layered.be.DogOwner;
import dk.easv.database3layered.dal.DogOwnerDAO;
import dk.easv.database3layered.exceptions.DogException;

import java.util.List;

public class Logic {
    DogOwnerDAO dao = new DogOwnerDAO();

    public List<DogOwner> getAllOwners() throws DogException {
        return dao.getAllOwners();
    }
}
