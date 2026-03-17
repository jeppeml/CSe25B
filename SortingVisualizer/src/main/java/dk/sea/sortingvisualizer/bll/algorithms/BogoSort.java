package dk.sea.sortingvisualizer.bll.algorithms;

import dk.sea.sortingvisualizer.bll.ISortingAlgorithm;
import dk.sea.sortingvisualizer.bll.MaxStepsReachedException;
import dk.sea.sortingvisualizer.bll.SortingContext;

public class BogoSort implements ISortingAlgorithm {
    @Override
    public String getName() { return "Bogo Sort"; }

    @Override
    public void sort(SortingContext context) {
        try {
            while (!context.isSorted()) {
                context.shuffle();
            }
        } catch (MaxStepsReachedException e) {
            // Cap reached — stop gracefully
        }
    }
}
