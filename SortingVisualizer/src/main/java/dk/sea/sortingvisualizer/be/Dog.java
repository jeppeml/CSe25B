package dk.sea.sortingvisualizer.be;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Random;

public class Dog implements ISortable {
    private static final String[] NAMES = {
        "Rex", "Buddy", "Max", "Bella", "Luna", "Charlie", "Daisy",
        "Rocky", "Molly", "Duke", "Sadie", "Bear", "Lucy", "Tucker",
        "Bailey", "Cooper", "Maggie", "Jack", "Chloe", "Zeus"
    };
    private static final Image[] DOG_IMAGES = {
        loadImage("/dk/sea/sortingvisualizer/images/dog_1.png"),  // puppy
        loadImage("/dk/sea/sortingvisualizer/images/dog_2.png"),  // terrier
        loadImage("/dk/sea/sortingvisualizer/images/dog_3.png"),  // golden retriever
        loadImage("/dk/sea/sortingvisualizer/images/dog_4.png"),  // husky
        loadImage("/dk/sea/sortingvisualizer/images/dog_5.png"),  // mastiff
        loadImage("/dk/sea/sortingvisualizer/images/dog_6.png"),  // great dane
    };

    private static Image loadImage(String path) {
        var stream = Dog.class.getResourceAsStream(path);
        return stream != null ? new Image(stream) : null;
    }

    private final String name;
    private final double size;

    public Dog(String name, double size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public double getSortValue() { return size; }

    @Override
    public String getLabel() { return name; }

    @Override
    public Image getRepresentation() {
        // size ranges 10-99, split into 6 tiers
        int tier = Math.min((int) ((size - 10) / 15), DOG_IMAGES.length - 1);
        tier = Math.max(0, tier);
        return DOG_IMAGES[tier];
    }

    @Override
    public String toString() { return name + " (" + (int) size + ")"; }

    public static ArrayList<ISortable> generateRandom(int count) {
        Random rng = new Random();
        ArrayList<ISortable> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = NAMES[rng.nextInt(NAMES.length)];
            double size = Math.clamp(rng.nextGaussian() * 20 + 55, 10, 99);
            list.add(new Dog(name, size));
        }
        return list;
    }
}
