package sprint1_0.product.service;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.MasterPlayer;
import sprint1_0.product.model.Position;
import sprint1_0.product.model.PositionCircle;

/** @author pujitha ravali rekha rakesh */
public class BoardInitService {

  public Board giveNewBoard() {
    Board newBoard = new Board();
    MasterPlayer p1 = new MasterPlayer();
    MasterPlayer p2 = new MasterPlayer();
    p1.setFilledPositions(new ArrayList<>());
    p2.setFilledPositions(new ArrayList<>());
    newBoard.setPlayer1(p1);
    newBoard.setPlayer2(p2);
    return newBoard;
  }

  /**
   * This method is used to setup the board GUI to business logic
   *
   * @param board
   */
  public void setUpBoard(Board board) {

    List<PositionCircle> allCirclesList = board.getPositionCircleList();

    List<Position> positionList = new ArrayList<>();
    for (int i = 0; i < GameConstants.NUM_POSITIONS_OF_BOARD; i++) {
      PositionCircle positionCircle = allCirclesList.get(i);
      Position position = new Position();
      position.setPositionCircle(positionCircle);
      position.setPositionId(i);
      positionList.add(position);
    }

    positionList.get(0).setRight(positionList.get(1));
    positionList.get(0).setDown(positionList.get(9));

    positionList.get(1).setRight(positionList.get(2));
    positionList.get(1).setLeft(positionList.get(0));
    positionList.get(1).setDown(positionList.get(4));

    positionList.get(2).setLeft(positionList.get(1));
    positionList.get(2).setDown(positionList.get(14));

    positionList.get(3).setRight(positionList.get(4));
    positionList.get(3).setDown(positionList.get(10));

    positionList.get(4).setLeft(positionList.get(3));
    positionList.get(4).setRight(positionList.get(5));
    positionList.get(4).setUp(positionList.get(1));
    positionList.get(4).setDown(positionList.get(7));

    positionList.get(5).setLeft(positionList.get(4));
    positionList.get(5).setDown(positionList.get(13));

    positionList.get(6).setRight(positionList.get(7));
    positionList.get(6).setDown(positionList.get(11));

    positionList.get(7).setLeft(positionList.get(6));
    positionList.get(7).setRight(positionList.get(8));
    positionList.get(7).setUp(positionList.get(4));

    positionList.get(8).setLeft(positionList.get(7));
    positionList.get(8).setDown(positionList.get(12));

    positionList.get(9).setRight(positionList.get(10));
    positionList.get(9).setUp(positionList.get(0));
    positionList.get(9).setDown(positionList.get(21));

    positionList.get(10).setRight(positionList.get(11));
    positionList.get(10).setLeft(positionList.get(9));
    positionList.get(10).setUp(positionList.get(3));
    positionList.get(10).setDown(positionList.get(18));

    positionList.get(11).setLeft(positionList.get(10));
    positionList.get(11).setUp(positionList.get(6));
    positionList.get(11).setDown(positionList.get(15));

    positionList.get(12).setRight(positionList.get(13));
    positionList.get(12).setUp(positionList.get(8));
    positionList.get(12).setDown(positionList.get(17));

    positionList.get(13).setRight(positionList.get(14));
    positionList.get(13).setLeft(positionList.get(12));
    positionList.get(13).setUp(positionList.get(5));
    positionList.get(13).setDown(positionList.get(20));

    positionList.get(14).setLeft(positionList.get(13));
    positionList.get(14).setUp(positionList.get(2));
    positionList.get(14).setDown(positionList.get(23));

    positionList.get(15).setRight(positionList.get(16));
    positionList.get(15).setUp(positionList.get(11));

    positionList.get(16).setLeft(positionList.get(15));
    positionList.get(16).setRight(positionList.get(17));
    positionList.get(16).setDown(positionList.get(19));

    positionList.get(17).setLeft(positionList.get(16));
    positionList.get(17).setUp(positionList.get(12));

    positionList.get(18).setRight(positionList.get(19));
    positionList.get(18).setUp(positionList.get(10));

    positionList.get(19).setRight(positionList.get(20));
    positionList.get(19).setLeft(positionList.get(18));
    positionList.get(19).setUp(positionList.get(16));
    positionList.get(19).setDown(positionList.get(22));

    positionList.get(20).setLeft(positionList.get(19));
    positionList.get(20).setUp(positionList.get(13));

    positionList.get(21).setRight(positionList.get(22));
    positionList.get(21).setUp(positionList.get(9));

    positionList.get(22).setRight(positionList.get(23));
    positionList.get(22).setLeft(positionList.get(21));
    positionList.get(22).setUp(positionList.get(19));

    positionList.get(23).setLeft(positionList.get(22));
    positionList.get(23).setUp(positionList.get(14));

    List<Position> filledPositionList = new ArrayList<>();
    List<Position> blankPositionList = new ArrayList<>();
    filledPositionList.addAll(positionList);
    blankPositionList.addAll(positionList);
    //positionList.forEach(position -> {filledPositionList.add(position.clone()));
    board.setFilledPositionList(filledPositionList);
    board.setBlankPositionList(blankPositionList);
  }

  /**
   * This method is used to RESET the board when RESET button is clicked
   *
   * @param board
   */
  public void resetGame(Board board) {
    EventHandler<javafx.scene.input.MouseEvent> resetGameButtonEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {

          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            board.getDecideButton().setDisable(true);
            board.getStartNewGameButton().setDisable(false);
            board.getResetGameButton().setDisable(true);
            board.getDisplayTextTurn().setText("Who's Turn?");
            board.getDisplayCircleTurn().setFill(Color.BLACK);
            for (int i = 0; i < board.getPositionCircleList().size(); i++) {
              board.getPositionCircleList().get(i).setFill(Color.ROSYBROWN);
              board.getPositionCircleList().get(i).setDisable(true);
            }
          }
        };
    board.getResetGameButton().setOnMouseClicked(resetGameButtonEventHandler);
  }

  /**
   * * This method is used to setup the NEW board GUI when NEW GAME BUTTON IS CLICKED
   *
   * @param board
   */
  public void newGame(Board board) {
    EventHandler<javafx.scene.input.MouseEvent> newGameButtonEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {

          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            board.getDecideButton().setDisable(false);
            board.getStartNewGameButton().setDisable(true);
            board.getResetGameButton().setDisable(false);
          }
        };
    board.getStartNewGameButton().setOnMouseClicked(newGameButtonEventHandler);
  }
}
