package dk.sea.sortingvisualizer.bll;

public class MaxStepsReachedException extends RuntimeException {
    public MaxStepsReachedException() {
        super("Maximum step count reached");
    }
}
