package dk.easv.database3layered.be;

import java.util.List;

public class Dog {
    private int id;
    private String name;
    private int birthYear;
    private int ownerId;


    public Dog(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Dog(int id, String name,  int birthYear){
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
    }

    public Dog(int id, String name, int birthYear, int ownerId) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getBirthYear() {
        return birthYear;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    @Override
    public String toString() {
        return getId() + ": " + getName() +  " " + getBirthYear() + " owner: " + ownerId;
    }


}
