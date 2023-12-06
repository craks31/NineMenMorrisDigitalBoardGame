package sprint1_0.product.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class RecordedMove {

  private Circle clickedCircle;

  private Color decidedColor;

  public Color getDecidedColor() {
    return decidedColor;
  }

  public void setDecidedColor(Color decidedColor) {
    this.decidedColor = decidedColor;
  }

  public Circle getClickedCircle() {
    return clickedCircle;
  }

  public void setClickedCircle(Circle clickedCircle) {
    this.clickedCircle = clickedCircle;
  }
}
