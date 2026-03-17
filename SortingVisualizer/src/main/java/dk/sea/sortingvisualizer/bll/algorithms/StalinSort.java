package dk.sea.sortingvisualizer.bll.algorithms;

import dk.sea.sortingvisualizer.bll.ISortingAlgorithm;
import dk.sea.sortingvisualizer.bll.SortingContext;

public class StalinSort implements ISortingAlgorithm {
    @Override
    public String getName() { return "Stalin Sort"; }

    @Override
    public void sort(SortingContext context) {
        int i = 1;
        while (i < context.size()) {
            if (context.compare(i, i - 1) < 0) {
                context.remove(i);
            } else {
                i++;
            }
        }
    }
}
