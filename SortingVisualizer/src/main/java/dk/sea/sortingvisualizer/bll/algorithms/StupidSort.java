package dk.sea.sortingvisualizer.bll.algorithms;

import dk.sea.sortingvisualizer.bll.ISortingAlgorithm;
import dk.sea.sortingvisualizer.bll.MaxStepsReachedException;
import dk.sea.sortingvisualizer.bll.SortingContext;
import java.util.Random;

public class StupidSort implements ISortingAlgorithm {
    @Override
    public String getName() { return "Stupid Sort"; }

    @Override
    public void sort(SortingContext context) {
        Random rng = new Random();
        try {
            while (!context.isSorted()) {
                int i = rng.nextInt(context.size());
                int j = rng.nextInt(context.size());
                if (i != j && context.compare(i, j) > 0) {
                    context.swap(i, j);
                }
            }
        } catch (MaxStepsReachedException e) {
            // Cap reached — stop gracefully
        }
    }
}
