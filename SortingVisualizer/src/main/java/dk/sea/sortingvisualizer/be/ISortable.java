package dk.sea.sortingvisualizer.be;

import javafx.scene.image.Image;

public interface ISortable extends Comparable<ISortable> {
    double getSortValue();
    default String getLabel() { return null; }
    default Image getRepresentation() { return null; }

    @Override
    default int compareTo(ISortable other) {
        return Double.compare(getSortValue(), other.getSortValue());
    }
}
