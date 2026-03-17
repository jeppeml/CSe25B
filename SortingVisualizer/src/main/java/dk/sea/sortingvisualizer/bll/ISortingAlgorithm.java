package dk.sea.sortingvisualizer.bll;

public interface ISortingAlgorithm {
    String getName();
    void sort(SortingContext context);
}
