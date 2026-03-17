package dk.sea.sortingvisualizer.bll.algorithms;

import dk.sea.sortingvisualizer.be.ISortable;
import dk.sea.sortingvisualizer.bll.ISortingAlgorithm;
import dk.sea.sortingvisualizer.bll.SortingContext;

public class MergeSort implements ISortingAlgorithm {
    @Override
    public String getName() { return "Merge Sort"; }

    @Override
    public void sort(SortingContext context) {
        mergeSort(context, 0, context.size() - 1);
    }

    private void mergeSort(SortingContext context, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(context, left, mid);
        mergeSort(context, mid + 1, right);
        merge(context, left, mid, right);
    }

    private void merge(SortingContext context, int left, int mid, int right) {
        context.setActiveRange(left, right);

        ISortable[] leftArr = new ISortable[mid - left + 1];
        ISortable[] rightArr = new ISortable[right - mid];
        for (int i = 0; i < leftArr.length; i++) leftArr[i] = context.get(left + i);
        for (int i = 0; i < rightArr.length; i++) rightArr[i] = context.get(mid + 1 + i);

        int i = 0, j = 0, k = left;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i].compareTo(rightArr[j]) <= 0) {
                context.set(k, leftArr[i]);
                i++;
            } else {
                context.set(k, rightArr[j]);
                j++;
            }
            k++;
        }
        while (i < leftArr.length) {
            context.set(k, leftArr[i]);
            i++;
            k++;
        }
        while (j < rightArr.length) {
            context.set(k, rightArr[j]);
            j++;
            k++;
        }
    }
}
