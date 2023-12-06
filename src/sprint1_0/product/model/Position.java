package sprint1_0.product.model;

import java.util.List;

import javafx.scene.paint.Paint;

/** @author rakesh */
public class Position extends PositionCircle {

  private int positionId;
  private PositionCircle positionCircle;
  private Position up = null;
  private Position down = null;
  private Position left = null;
  private Position right = null;
  private boolean isPartOfHorMill;
  private boolean isPartOfVerMill;
  private boolean isFilled;
  private List<Position> horMillFamily;
  private List<Position> verMillFamily;

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

  public List<Position> getHorMillFamily() {
    return horMillFamily;
  }

  public void setHorMillFamily(List<Position> horMillFamily) {
    this.horMillFamily = horMillFamily;
  }

  public List<Position> getVerMillFamily() {
    return verMillFamily;
  }

  public void setVerMillFamily(List<Position> verMillFamily) {
    this.verMillFamily = verMillFamily;
  }

  public boolean isPartOfVerMill() {
    return isPartOfVerMill;
  }

  public void setPartOfVerMill(boolean isPartOfVerMill) {
    this.isPartOfVerMill = isPartOfVerMill;
  }

  public boolean isPartOfHorMill() {
    return isPartOfHorMill;
  }

  public void setPartOfHorMill(boolean isPartOfHorMill) {
    this.isPartOfHorMill = isPartOfHorMill;
  }
}
