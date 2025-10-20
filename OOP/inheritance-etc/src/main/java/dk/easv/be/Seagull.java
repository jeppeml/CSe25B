package dk.easv.be;

public class Seagull extends Bird {

    @Override
    public void flyAway() {
        System.out.println("Seagull flying away gracefully");
    }

    @Override
    public void makeSound() {
        System.out.println("Gruar!");
    }
}
