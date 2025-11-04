package dk.easv;

import dk.easv.be.*;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Seagull s = new Seagull();
        s.makeSound();
        s.flyAway();

        System.out.println("health: " +  s.getHealth());

        Chicken chicky = new Chicken();
        chicky.makeSound();
        chicky.killAllBloodyChickens();
        System.out.println("health: " +  chicky.getHealth());

        Bird chicky2 = new Chicken();
        chicky2.makeSound();
        chicky2.flyAway();
        System.out.println("health: " +  chicky2.getHealth());


        Soundmaking someSoundmakingThing = new Seagull();
        someSoundmakingThing.makeSound();

        List list = new LinkedList<>(); // Polymorphism and indirection

        System.out.println("Saluton miaj amikoj, renkonti");
    }
}