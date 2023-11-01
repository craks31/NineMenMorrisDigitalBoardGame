package sprint1_0.product.model;

import javafx.scene.paint.Paint;

/** @author rakesh */
public class Position extends PositionCircle implements Cloneable {

  private int positionId;
  private PositionCircle positionCircle;

  private Position up = null;
  private Position down = null;
  private Position left = null;
  private Position right = null;
  private boolean isFilled;

  public Position() {
    super();
  }

  public Position(double radius, double centerX, double centerY, double strokeWidth, Paint color) {
    super(radius, centerX, centerY, strokeWidth, color);
  }

  public Position(double centerX, double centerY, double radius, Paint fill) {
    super(centerX, centerY, radius, fill);
  }

  public Position(double centerX, double centerY, double radius) {
    super(centerX, centerY, radius);
  }

  public Position(double radius, Paint fill) {
    super(radius, fill);
  }

  public Position(double radius) {
    super(radius);
  }

  public Position(int positionId, PositionCircle positionCircle) {
    this.positionId = positionId;
    this.positionCircle = positionCircle;
  }

  public int getPositionId() {
    return positionId;
  }

  public void setPositionId(int positionId) {
    this.positionId = positionId;
  }

  public Position getUp() {
    return up;
  }

  public void setUp(Position up) {
    this.up = up;
  }

  public Position getDown() {
    return down;
  }

  public void setDown(Position down) {
    this.down = down;
  }

  public Position getLeft() {
    return left;
  }

  public void setLeft(Position left) {
    this.left = left;
  }

  public Position getRight() {
    return right;
  }

  public void setRight(Position right) {
    this.right = right;
  }

  public PositionCircle getPositionCircle() {
    return positionCircle;
  }

  public void setPositionCircle(PositionCircle positionCircle) {
    this.positionCircle = positionCircle;
  }

  public boolean isFilled() {
    return isFilled;
  }

  public void setFilled(boolean isFilled) {
    this.isFilled = isFilled;
  }
}
