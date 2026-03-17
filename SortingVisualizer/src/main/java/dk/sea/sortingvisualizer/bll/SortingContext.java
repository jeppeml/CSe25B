package dk.sea.sortingvisualizer.bll;

import dk.sea.sortingvisualizer.be.ISortable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingContext {
    private static final int MAX_STEPS = 100_000;

    private final ArrayList<ISortable> data;
    private final List<ISortable> originalData;
    private final List<SortStep> steps = new ArrayList<>();
    private int comparisons = 0;
    private int swaps = 0;
    private int removes = 0;
    private int activeRangeStart = -1;
    private int activeRangeEnd = -1;
    private int pivotIndex = -1;
    private boolean capReached = false;

    public SortingContext(List<ISortable> data) {
        this.data = new ArrayList<>(data);
        this.originalData = new ArrayList<>(data);
    }

    public int compare(int i, int j) {
        if (capReached) throw new MaxStepsReachedException();
        int result = data.get(i).compareTo(data.get(j));
        comparisons++;
        recordStep(StepType.COMPARE, i, j);
        return result;
    }

    public void swap(int i, int j) {
        if (capReached) throw new MaxStepsReachedException();
        Collections.swap(data, i, j);
        swaps++;
        recordStep(StepType.SWAP, i, j);
    }

    public void remove(int i) {
        if (capReached) throw new MaxStepsReachedException();
        data.remove(i);
        removes++;
        recordStep(StepType.REMOVE, i, -1);
    }

    public void set(int i, ISortable value) {
        if (capReached) throw new MaxStepsReachedException();
        data.set(i, value);
        recordStep(StepType.SET, i, -1);
    }

    public ISortable get(int i) {
        return data.get(i);
    }

    public int size() {
        return data.size();
    }

    public boolean isSorted() {
        for (int i = 0; i < data.size() - 1; i++) {
            if (data.get(i).compareTo(data.get(i + 1)) > 0) return false;
        }
        return true;
    }

    public void shuffle() {
        if (capReached) throw new MaxStepsReachedException();
        Collections.shuffle(data);
        recordStep(StepType.SHUFFLE, -1, -1);
    }

    public void setActiveRange(int start, int end) {
        this.activeRangeStart = start;
        this.activeRangeEnd = end;
    }

    public void setPivot(int index) {
        this.pivotIndex = index;
    }

    private void recordStep(StepType type, int indexA, int indexB) {
        steps.add(new SortStep(type, indexA, indexB,
                activeRangeStart, activeRangeEnd, pivotIndex,
                new ArrayList<>(data)));
        if (steps.size() >= MAX_STEPS) {
            capReached = true;
        }
    }

    public List<SortStep> getSteps() { return steps; }
    public int getComparisonCount() { return comparisons; }
    public int getSwapCount() { return swaps; }
    public int getRemoveCount() { return removes; }
    public List<ISortable> getOriginalData() { return new ArrayList<>(originalData); }
    public boolean isCapReached() { return capReached; }
}
