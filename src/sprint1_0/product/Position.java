package sprint1_0.product;

public class Position extends PositionCircle {

  private int positionId;
  private double x;
  private double y;
  
  private Position up = null;
  private Position down = null;
  private Position left = null;
  private Position right = null;
  
  

  public double getX() {
return x;}

public void setX(double x) {
this.x = x;}

public double getY() {
return y;}

public void setY(double y) {
this.y = y;}

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
}
