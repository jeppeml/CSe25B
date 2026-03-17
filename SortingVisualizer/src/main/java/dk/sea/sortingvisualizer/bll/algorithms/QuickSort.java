package dk.sea.sortingvisualizer.bll.algorithms;

import dk.sea.sortingvisualizer.bll.ISortingAlgorithm;
import dk.sea.sortingvisualizer.bll.SortingContext;

public class QuickSort implements ISortingAlgorithm {
    @Override
    public String getName() { return "Quick Sort"; }

    @Override
    public void sort(SortingContext context) {
        quickSort(context, 0, context.size() - 1);
    }

    private void quickSort(SortingContext context, int low, int high) {
        if (low >= high) return;
        context.setActiveRange(low, high);
        int pivotIdx = partition(context, low, high);
        quickSort(context, low, pivotIdx - 1);
        quickSort(context, pivotIdx + 1, high);
    }

    private int partition(SortingContext context, int low, int high) {
        context.setPivot(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (context.compare(j, high) < 0) {
                i++;
                context.swap(i, j);
            }
        }
        context.swap(i + 1, high);
        return i + 1;
    }
}
