package dk.easv.database3layered.dal;

import dk.easv.database3layered.be.DogOwner;
import dk.easv.database3layered.exceptions.DogException;

import java.util.ArrayList;
import java.util.List;

public class MockDogOwnerDAO implements IDogOwnerDAO {
    public List<DogOwner> getAllOwners() throws DogException {
        List<DogOwner> dogOwners = new ArrayList<>();
        return dogOwners;
    }
}
