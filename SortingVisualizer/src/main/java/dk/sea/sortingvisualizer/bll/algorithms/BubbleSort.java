package dk.sea.sortingvisualizer.bll.algorithms;

import dk.sea.sortingvisualizer.bll.ISortingAlgorithm;
import dk.sea.sortingvisualizer.bll.SortingContext;

public class BubbleSort implements ISortingAlgorithm {
    @Override
    public String getName() { return "Bubble Sort"; }

    @Override
    public void sort(SortingContext context) {
        int n = context.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (context.compare(j, j + 1) > 0) {
                    context.swap(j, j + 1);
                }
            }
        }
    }
}
