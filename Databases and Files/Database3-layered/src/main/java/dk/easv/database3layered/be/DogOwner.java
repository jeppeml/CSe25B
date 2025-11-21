package dk.easv.database3layered.be;

import java.util.ArrayList;
import java.util.List;

public class DogOwner {
    private int id;
    private String name;
    private List<Dog> dogs;

    public DogOwner(int id){
        this.id = id;
        dogs = new ArrayList<>();
    }

    public DogOwner(int id, String name){
        this.id = id;
        this.name = name;
        dogs = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    @Override
    public String toString() {
        return getId() + ": " + getName();
    }
}
