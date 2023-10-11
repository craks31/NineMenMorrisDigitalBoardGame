package sprint1_0.product.service;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.MasterPlayer;
import sprint1_0.product.model.Position;
import sprint1_0.product.model.PositionCircle;

public class BoardInitService {

  public Board giveNewBoard() {
    Board newBoard = new Board();
    MasterPlayer p1 = new MasterPlayer();
    MasterPlayer p2 = new MasterPlayer();
    newBoard.setPlayer1(p1);
    newBoard.setPlayer2(p2);
    return newBoard;
  }

  public void setUpBoard(Board board) {

    List<PositionCircle> blankCircles = board.getPositionCircleList();

    List<Position> blankPositionList = new ArrayList<>();
    for (int i = 0; i < board.NUM_POSITIONS_OF_BOARD; i++) {
      PositionCircle blankPositionCircle = blankCircles.get(i);
      Position blankPosition = new Position();
      blankPosition.setPositionCircle(blankPositionCircle);
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

    board.setBlankPositionList(blankPositionList);
  }

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
              board
                  .getPositionCircleList()
                  .get(i)
                  .setDisable(true);
                  
            }
          }
        };
    board.getResetGameButton().setOnMouseClicked(resetGameButtonEventHandler);
  }

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
