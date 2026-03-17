package dk.sea.sortingvisualizer.bll.algorithms;

import dk.sea.sortingvisualizer.bll.ISortingAlgorithm;
import dk.sea.sortingvisualizer.bll.SortingContext;

public class SelectionSort implements ISortingAlgorithm {
    @Override
    public String getName() { return "Selection Sort"; }

    @Override
    public void sort(SortingContext context) {
        int n = context.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (context.compare(j, minIdx) < 0) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                context.swap(i, minIdx);
            }
        }
    }
}
