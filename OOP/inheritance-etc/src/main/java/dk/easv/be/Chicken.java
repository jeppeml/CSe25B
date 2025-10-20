package dk.easv.be;

public class Chicken extends Bird {
    @Override
    public void flyAway() {
        System.out.println("Chicken fails miserably at flying :(");
    }

    @Override
    public void makeSound() {
        System.out.println("Rhuuuarg!");

    }

    public void killAllBloodyChickens(){
        System.out.println("Look for blood, if blood kill!");
    }
}
