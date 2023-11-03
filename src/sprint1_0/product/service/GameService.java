package sprint1_0.product.service;

import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sprint1_0.product.helper.DisplayUtil;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.PositionCircle;

public class GameService {
  DisplayUtil displayUtil = new DisplayUtil();
  CoinPlacementService coinPlacementService = new CoinPlacementService();
  CoinMovementService coinMovementService = new CoinMovementService();
  /**
   * This method is used to deicde a player turn randomly.
   *
   * @param board
   * @return
   */
  public Color decidePlayerColorTurn(Board board) {
    Random rand = new Random();
    boolean isPlayer1 = rand.nextBoolean();
    if (isPlayer1) {
      return board.getPlayer1().getPlayerColor();
    } else {
      return board.getPlayer2().getPlayerColor();
    }
  }

  public void initDisplayAndStartGame(Board board, Color decidedColor) {

    EventHandler<javafx.scene.input.MouseEvent> decideButtonEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {

          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            displayUtil.displayPlayerTurn(board, decidedColor);
            board.getDecideButton().setDisable(true);
            placeCoinsSetup(board);
          }
        };

    // Adding the event handler
    board.getDecideButton().setOnMouseClicked(decideButtonEventHandler);
  }

  public void startGame(Board board, Color decideColor) {
    initDisplayAndStartGame(board, decideColor);
  }
  
  public void placeCoinsSetup(Board board) {
	    board.setOp("FILL");
	    for (int i = 0; i < board.getBlankPositionList().size(); i++) {
	      PositionCircle positionCircle = board.getBlankPositionList().get(i).getPositionCircle();
	      // Adding the event handler
	      positionCircle.setDisable(false);
	      positionCircle.addEventHandler(MouseEvent.MOUSE_CLICKED, coinMouseEventHandler(board));
	      positionCircle.setUserData(i);
	    }
	  }

	  private EventHandler<MouseEvent> coinMouseEventHandler(Board board) {
	    EventHandler<MouseEvent> coinMouseEventHandler =
	        new EventHandler<javafx.scene.input.MouseEvent>() {

	          @Override
	          public void handle(javafx.scene.input.MouseEvent e) {
	            Circle clickedCircle = (Circle) e.getSource();
	            if (board.getOp().equals("FILL")) {
	              coinPlacementService.coinFillEvent(board, clickedCircle);
	            } else if (board.getOp().equals("MOVE")) {
	              coinMovementService.coinMoveEvent(board, clickedCircle);
	            }
	            clickedCircle.setDisable(true);
	          }
	        };
	    return coinMouseEventHandler;
	  }
}
