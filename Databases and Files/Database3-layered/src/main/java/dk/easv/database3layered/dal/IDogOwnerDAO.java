package dk.easv.database3layered.dal;

import dk.easv.database3layered.be.DogOwner;
import dk.easv.database3layered.exceptions.DogException;

import java.util.List;

public interface IDogOwnerDAO {
    List<DogOwner> getAllOwners() throws DogException;

    }
