package sprint1_0.product;

import java.util.List;

public class Board {
  public static final int NUM_POSITIONS_OF_BOARD = 24;
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

  public static int getNumPositionsOfBoard() {
    return NUM_POSITIONS_OF_BOARD;
  }

  public void initPlayers() {
	  player1 = new MasterPlayer();
	  player2 = new MasterPlayer();
	  
	  player1.getPlayerColor();
	  
  }
  public void setUpBoard() {
	  
	GUI boardGUI = new GUI();
    List<PositionCircle> blankCircles = boardGUI.getPositionCircleList();
    for (int i = 0; i < NUM_POSITIONS_OF_BOARD; i++) {
      Position blankPosition = (Position) blankCircles.get(i);
      blankPositionList.add(blankPosition);
      blankPosition.setPositionId(i);
    }

    blankPositionList.get(0).setRight(blankPositionList.get(1));
    blankPositionList.get(0).setDown(blankPositionList.get(9));

    blankPositionList.get(1).setRight(blankPositionList.get(2));
    blankPositionList.get(1).setLeft(blankPositionList.get(0));
    blankPositionList.get(1).setDown(blankPositionList.get(4));

    blankPositionList.get(2).setLeft(blankPositionList.get(1));
    blankPositionList.get(2).setDown(blankPositionList.get(14));

    blankPositionList.get(3).setRight(blankPositionList.get(4));
    blankPositionList.get(3).setDown(blankPositionList.get(10));

    blankPositionList.get(4).setLeft(blankPositionList.get(3));
    blankPositionList.get(4).setRight(blankPositionList.get(5));
    blankPositionList.get(4).setUp(blankPositionList.get(1));
    blankPositionList.get(4).setDown(blankPositionList.get(7));

    blankPositionList.get(5).setLeft(blankPositionList.get(4));
    blankPositionList.get(5).setDown(blankPositionList.get(13));

    blankPositionList.get(6).setRight(blankPositionList.get(7));
    blankPositionList.get(6).setDown(blankPositionList.get(11));

    blankPositionList.get(7).setLeft(blankPositionList.get(6));
    blankPositionList.get(7).setRight(blankPositionList.get(8));
    blankPositionList.get(7).setUp(blankPositionList.get(4));

    blankPositionList.get(8).setLeft(blankPositionList.get(7));
    blankPositionList.get(8).setDown(blankPositionList.get(12));

    blankPositionList.get(9).setRight(blankPositionList.get(10));
    blankPositionList.get(9).setUp(blankPositionList.get(0));
    blankPositionList.get(9).setDown(blankPositionList.get(21));

    blankPositionList.get(10).setRight(blankPositionList.get(11));
    blankPositionList.get(10).setLeft(blankPositionList.get(9));
    blankPositionList.get(10).setUp(blankPositionList.get(3));
    blankPositionList.get(10).setDown(blankPositionList.get(18));

    blankPositionList.get(11).setLeft(blankPositionList.get(10));
    blankPositionList.get(11).setUp(blankPositionList.get(6));
    blankPositionList.get(11).setDown(blankPositionList.get(15));

    blankPositionList.get(12).setRight(blankPositionList.get(13));
    blankPositionList.get(12).setUp(blankPositionList.get(8));
    blankPositionList.get(12).setDown(blankPositionList.get(17));

    blankPositionList.get(13).setRight(blankPositionList.get(14));
    blankPositionList.get(13).setLeft(blankPositionList.get(12));
    blankPositionList.get(13).setUp(blankPositionList.get(5));
    blankPositionList.get(13).setDown(blankPositionList.get(20));

    blankPositionList.get(14).setLeft(blankPositionList.get(13));
    blankPositionList.get(14).setUp(blankPositionList.get(2));
    blankPositionList.get(14).setDown(blankPositionList.get(23));

    blankPositionList.get(15).setRight(blankPositionList.get(16));
    blankPositionList.get(15).setUp(blankPositionList.get(11));

    blankPositionList.get(16).setLeft(blankPositionList.get(15));
    blankPositionList.get(16).setRight(blankPositionList.get(17));
    blankPositionList.get(16).setDown(blankPositionList.get(19));

    blankPositionList.get(17).setLeft(blankPositionList.get(16));
    blankPositionList.get(17).setUp(blankPositionList.get(12));

    blankPositionList.get(18).setRight(blankPositionList.get(19));
    blankPositionList.get(18).setUp(blankPositionList.get(10));

    blankPositionList.get(19).setRight(blankPositionList.get(20));
    blankPositionList.get(19).setLeft(blankPositionList.get(18));
    blankPositionList.get(19).setUp(blankPositionList.get(16));
    blankPositionList.get(19).setDown(blankPositionList.get(22));

    blankPositionList.get(20).setLeft(blankPositionList.get(19));
    blankPositionList.get(20).setUp(blankPositionList.get(13));

    blankPositionList.get(21).setRight(blankPositionList.get(22));
    blankPositionList.get(21).setUp(blankPositionList.get(9));

    blankPositionList.get(22).setRight(blankPositionList.get(23));
    blankPositionList.get(22).setLeft(blankPositionList.get(21));
    blankPositionList.get(22).setUp(blankPositionList.get(19));

    blankPositionList.get(23).setLeft(blankPositionList.get(22));
    blankPositionList.get(23).setUp(blankPositionList.get(14));
    setBlankPositionList(blankPositionList);
  }
}
