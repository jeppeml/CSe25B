package dk.sea.sortingvisualizer.be;

import java.util.ArrayList;
import java.util.Random;

public class NumberItem implements ISortable {
    private final int value;

    public NumberItem(int value) {
        this.value = value;
    }

    @Override
    public double getSortValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static ArrayList<ISortable> generateRandom(int count) {
        Random rng = new Random();
        ArrayList<ISortable> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new NumberItem((int) Math.clamp(rng.nextGaussian() * 25 + 50, 1, 99)));
        }
        return list;
    }
}
