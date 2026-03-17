package dk.sea.sortingvisualizer.gui;

import dk.sea.sortingvisualizer.be.ISortable;
import dk.sea.sortingvisualizer.bll.SortStep;
import dk.sea.sortingvisualizer.bll.StepType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import java.util.List;

public class SortingVisualization extends Canvas {
    private static final double LABEL_HEIGHT = 20;
    private static final double TOP_PADDING = 20;
    private static final Color COLOR_DEFAULT = Color.STEELBLUE;
    private static final Color COLOR_COMPARE = Color.GOLD;
    private static final Color COLOR_SWAP = Color.TOMATO;
    private static final Color COLOR_REMOVE = Color.GRAY;
    private static final Color COLOR_RANGE = Color.LIGHTBLUE;
    private static final Color COLOR_PIVOT = Color.LIMEGREEN;
    private static final Color COLOR_SET = Color.ORANGE;

    private List<ISortable> data;
    private SortStep currentStep;

    public SortingVisualization() {
        widthProperty().addListener(e -> draw());
        heightProperty().addListener(e -> draw());
    }

    @Override
    public boolean isResizable() { return false; }

    @Override
    public double prefWidth(double height) { return 0; }

    @Override
    public double prefHeight(double width) { return 0; }

    @Override
    public double minWidth(double height) { return 0; }

    @Override
    public double minHeight(double width) { return 0; }

    @Override
    public double maxWidth(double height) { return Double.MAX_VALUE; }

    @Override
    public double maxHeight(double width) { return Double.MAX_VALUE; }

    public void showData(List<ISortable> data, SortStep step) {
        this.data = data;
        this.currentStep = step;
        draw();
    }

    public void clear() {
        this.data = null;
        this.currentStep = null;
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
    }

    private void draw() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        if (data == null || data.isEmpty()) return;

        int count = data.size();
        double areaWidth = getWidth();
        double areaHeight = getHeight() - LABEL_HEIGHT - TOP_PADDING;
        double barWidth = areaWidth / count;
        double maxVal = data.stream()
                .mapToDouble(ISortable::getSortValue)
                .max().orElse(1);

        gc.setFont(Font.font(Math.min(12, barWidth - 2)));
        gc.setTextAlign(TextAlignment.CENTER);

        for (int i = 0; i < count; i++) {
            ISortable item = data.get(i);
            double value = item.getSortValue();
            double barHeight = (value / maxVal) * areaHeight;
            double x = i * barWidth;
            double y = TOP_PADDING + areaHeight - barHeight;

            Color color = getColorForIndex(i);

            Image img = item.getRepresentation();
            if (img != null) {
                // Height driven by sort value, width fits within slot
                double imgW = barWidth - 4;
                double imgH = barHeight;
                double imgX = x + 2;
                double imgY = TOP_PADDING + areaHeight - imgH;

                gc.setGlobalAlpha(color == COLOR_DEFAULT ? 1.0 : 0.7);
                gc.drawImage(img, imgX, imgY, imgW, imgH);
                gc.setGlobalAlpha(1.0);
                if (color != COLOR_DEFAULT) {
                    gc.setFill(color.deriveColor(0, 1, 1, 0.3));
                    gc.fillRect(imgX, imgY, imgW, imgH);
                }
            } else {
                gc.setFill(color);
                gc.fillRect(x + 1, y, barWidth - 2, barHeight);
            }

            // Draw value on top of bar — black outline for readability
            String valueText = String.valueOf((int) value);
            if (barHeight > 15) {
                double tx = x + barWidth / 2;
                double ty = y + 14;
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(3);
                gc.strokeText(valueText, tx, ty);
                gc.setFill(Color.WHITE);
                gc.fillText(valueText, tx, ty);
            }

            // Draw label below — black outline for readability
            String label = item.getLabel();
            if (label == null) label = valueText;
            double lx = x + barWidth / 2;
            double ly = TOP_PADDING + areaHeight + LABEL_HEIGHT - 4;
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(2);
            gc.strokeText(label, lx, ly);
            gc.setFill(Color.BLACK);
            gc.fillText(label, lx, ly);
        }
    }

    private Color getColorForIndex(int i) {
        if (currentStep == null) return COLOR_DEFAULT;

        boolean isA = (currentStep.indexA() == i);
        boolean isB = (currentStep.indexB() == i);

        if (isA || isB) {
            return switch (currentStep.type()) {
                case COMPARE -> COLOR_COMPARE;
                case SWAP -> COLOR_SWAP;
                case REMOVE -> COLOR_REMOVE;
                case SET -> COLOR_SET;
                case SHUFFLE -> COLOR_DEFAULT;
            };
        }

        if (currentStep.pivotIndex() == i) return COLOR_PIVOT;

        if (currentStep.rangeStart() >= 0 && currentStep.rangeEnd() >= 0
                && i >= currentStep.rangeStart() && i <= currentStep.rangeEnd()) {
            return COLOR_RANGE;
        }

        return COLOR_DEFAULT;
    }
}
