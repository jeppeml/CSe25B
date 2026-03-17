package dk.sea.sortingvisualizer.be;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Random;

public class Person implements ISortable {
    private static final String[] NAMES = {
        "Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace",
        "Hank", "Ivy", "Jack", "Karen", "Leo", "Mia", "Noah",
        "Olivia", "Pete", "Quinn", "Ryan", "Sara", "Tom"
    };
    private static final Image[] PERSON_IMAGES = {
        loadImage("/dk/sea/sortingvisualizer/images/person_1.png"),  // toddler
        loadImage("/dk/sea/sortingvisualizer/images/person_2.png"),  // child
        loadImage("/dk/sea/sortingvisualizer/images/person_3.png"),  // teen
        loadImage("/dk/sea/sortingvisualizer/images/person_4.png"),  // adult woman
        loadImage("/dk/sea/sortingvisualizer/images/person_5.png"),  // adult man
        loadImage("/dk/sea/sortingvisualizer/images/person_6.png"),  // tall person
    };

    private static Image loadImage(String path) {
        var stream = Person.class.getResourceAsStream(path);
        return stream != null ? new Image(stream) : null;
    }

    private final String name;
    private final double height;

    public Person(String name, double height) {
        this.name = name;
        this.height = height;
    }

    @Override
    public double getSortValue() { return height; }

    @Override
    public String getLabel() { return name; }

    @Override
    public Image getRepresentation() {
        // height ranges 50-200, split into 6 tiers (~25cm each)
        int tier = Math.min((int) ((height - 50) / 25.0), PERSON_IMAGES.length - 1);
        tier = Math.max(0, tier);
        return PERSON_IMAGES[tier];
    }

    @Override
    public String toString() { return name + " (" + (int) height + " cm)"; }

    public static ArrayList<ISortable> generateRandom(int count) {
        Random rng = new Random();
        ArrayList<ISortable> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = NAMES[rng.nextInt(NAMES.length)];
            double height = Math.clamp(rng.nextGaussian() * 35 + 125, 50, 200);
            list.add(new Person(name, height));
        }
        return list;
    }
}
