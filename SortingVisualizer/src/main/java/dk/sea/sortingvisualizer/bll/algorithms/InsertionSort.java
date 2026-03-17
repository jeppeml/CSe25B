package dk.sea.sortingvisualizer.bll.algorithms;

import dk.sea.sortingvisualizer.bll.ISortingAlgorithm;
import dk.sea.sortingvisualizer.bll.SortingContext;

public class InsertionSort implements ISortingAlgorithm {
    @Override
    public String getName() { return "Insertion Sort"; }

    @Override
    public void sort(SortingContext context) {
        int n = context.size();
        for (int i = 1; i < n; i++) {
            int j = i;
            while (j > 0 && context.compare(j - 1, j) > 0) {
                context.swap(j - 1, j);
                j--;
            }
        }
    }
}
