package dk.sea.sortingvisualizer.bll;

import dk.sea.sortingvisualizer.be.ISortable;
import java.util.List;

public record SortStep(
    StepType type,
    int indexA,
    int indexB,
    int rangeStart,
    int rangeEnd,
    int pivotIndex,
    List<ISortable> snapshot
) {}
