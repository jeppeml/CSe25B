package dk.easv.database3layered.be;

public class DogOwner {
    private int id;
    private String name;

    public DogOwner(int id){
        this.id = id;
    }

    public DogOwner(int id, String name){
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return getId() + ": " + getName();
    }
}
