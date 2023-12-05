package sprint1_0.product.model;

import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/** @author rakesh */
public class MasterPlayer {

  private String playerName;
  private Color playerColor;
  private List<Circle> coins;
  private List<Position> filledPositions;
  static final int maxCoins = 9;

  public Color getPlayerColor() {
    return playerColor;
  }

  public void setPlayerColor(Color playerColor) {
    this.playerColor = playerColor;
  }

  public List<Circle> getCoins() {
    return coins;
  }

  public void setCoins(List<Circle> coins) {
    this.coins = coins;
  }

  public static int getMaxcoins() {
    return maxCoins;
  }

  public List<Position> getFilledPositions() {
    return filledPositions;
  }

  public void setFilledPositions(List<Position> filledPositions) {
    this.filledPositions = filledPositions;
  }

public String getPlayerName(){return playerName;}

public void setPlayerName(String playerName){this.playerName = playerName;}
}
