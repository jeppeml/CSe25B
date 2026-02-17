package dk.easv.database3layered.bll;

import dk.easv.database3layered.be.Dog;
import dk.easv.database3layered.be.DogOwner;
import dk.easv.database3layered.dal.DogsDAO;
import dk.easv.database3layered.dal.IDogOwnerDAO;
import dk.easv.database3layered.dal.MockDogOwnerDAO;
import dk.easv.database3layered.exceptions.DogException;

import java.util.List;

public class Logic {
    IDogOwnerDAO dao; //new DogOwnerDAO();
    DogsDAO dogsDAO = new DogsDAO();

    public Logic(IDogOwnerDAO dogOwnerDao) {
        dao = dogOwnerDao;
    }

    public List<DogOwner> getAllOwners() throws DogException {
        // check if ownerlist > 0
        // if 0 do something
        return dao.getAllOwners();
    }

    public List<Dog> getAllDogs() throws DogException {
        return dogsDAO.getAllDogs();
    }
}
