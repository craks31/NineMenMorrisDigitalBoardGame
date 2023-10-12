package sprint1_0.product.model;

import javafx.scene.paint.Paint;

/**
 * @author rakesh
 *
 */
public class Position extends PositionCircle {

  private int positionId;
  private PositionCircle positionCircle;
  private double x;
  private double y;

  private Position up = null;
  private Position down = null;
  private Position left = null;
  private Position right = null;

  public Position() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Position(double radius, double centerX, double centerY, double strokeWidth, Paint color) {
    super(radius, centerX, centerY, strokeWidth, color);
    // TODO Auto-generated constructor stub
  }

  public Position(double centerX, double centerY, double radius, Paint fill) {
    super(centerX, centerY, radius, fill);
    // TODO Auto-generated constructor stub
  }

  public Position(double centerX, double centerY, double radius) {
    super(centerX, centerY, radius);
    // TODO Auto-generated constructor stub
  }

  public Position(double radius, Paint fill) {
    super(radius, fill);
    // TODO Auto-generated constructor stub
  }

  public Position(double radius) {
    super(radius);
    // TODO Auto-generated constructor stub
  }


public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
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

public PositionCircle getPositionCircle(){return positionCircle;}

public void setPositionCircle(PositionCircle positionCircle){this.positionCircle = positionCircle;}
}
