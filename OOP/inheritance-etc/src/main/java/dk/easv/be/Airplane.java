package dk.easv.be;

public class Airplane implements Flyable, Soundmaking {

    @Override
    public void flyAway() {
        System.out.println("airplane flying");
    }

    @Override
    public void makeSound() {
        System.out.println("Brrrrrrrrrrrrr!");
    }
}
