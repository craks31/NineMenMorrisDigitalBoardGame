package sprint1_0.product.model;

import java.util.List;

public class Board {
  public final int NUM_POSITIONS_OF_BOARD = 24;
  private List<Position> blankPositionList;
  private List<Position> filledPositionList;
  private MasterPlayer player1;
  private MasterPlayer player2;
  
  public List<Position> getBlankPositionList() {
    return blankPositionList;
  }

  public void setBlankPositionList(List<Position> blankPositionList) {
    this.blankPositionList = blankPositionList;
  }

  public List<Position> getFilledPositionList() {
    return filledPositionList;
  }

  public void setFilledPositionList(List<Position> filledPositionList) {
    this.filledPositionList = filledPositionList;
  }

  public MasterPlayer getPlayer1() {
    return player1;
  }

  public void setPlayer1(MasterPlayer player1) {
    this.player1 = player1;
  }

  public MasterPlayer getPlayer2() {
    return player2;
  }

  public void setPlayer2(MasterPlayer player2) {
    this.player2 = player2;
  }
  
}
