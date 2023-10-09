package sprint1_0.product.model;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class PositionCircle extends Circle {

  public PositionCircle() {
    super();
  }

  public PositionCircle(
      double radius, double centerX, double centerY, double strokeWidth, Paint color) {
    Circle circle = new Circle(centerX, centerY, radius, color);
    circle.setStrokeWidth(strokeWidth);
  }

  public PositionCircle(double centerX, double centerY, double radius, Paint fill) {
    super(centerX, centerY, radius, fill);
  }

  public PositionCircle(double centerX, double centerY, double radius) {
    super(centerX, centerY, radius);
  }

  public PositionCircle(double radius, Paint fill) {
    super(radius, fill);
  }

  public PositionCircle(double radius) {
    super(radius);
  }
  
  
}
