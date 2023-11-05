package sprint1_0.product.service;

import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.StrokeTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import sprint1_0.product.helper.DisplayUtil;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;
import sprint1_0.product.model.PositionCircle;

public class GameService {
  DisplayUtil displayUtil = new DisplayUtil();
  CoinPlacementService coinPlacementService = new CoinPlacementService();
  CoinMovementService coinMovementService = new CoinMovementService();
  CoinRemovalService coinRemovalService = new CoinRemovalService();
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
            StrokeTransition strokeTransition = new StrokeTransition(Duration.seconds(10.5));
            strokeTransition.setFromValue(Color.BLUE); // Initial outer color
            strokeTransition.setToValue(Color.RED);     // Blinking outer color
            strokeTransition.setCycleCount(Animation.INDEFINITE);
            if (board.getOp().equals("FILL")) {
              coinPlacementService.coinFillEvent(board, clickedCircle);
              if (checkMill(
                  board,
                  board.getAllPositionList().get((int) clickedCircle.getUserData()),
                  clickedCircle.getFill())) {
                coinRemovalService.prepareForCoinRemoval(board, clickedCircle.getFill());
              }
              clickedCircle.setDisable(true);
            } else if (board.getOp().equals("MOVE")) {
              coinMovementService.coinMoveEvent(board, clickedCircle, strokeTransition);
              board.setPhase2Started(true);
              clickedCircle.setDisable(true);
            } else if (board.getOp().equals("REMOVE")) {
              coinRemovalService.coinRemoveEvent(board, clickedCircle, strokeTransition);
              clickedCircle.setDisable(false);
            }
            else if (board.getOp().equals("FLY")) {
               // coinRemovalService.coinRemoveEvent(board, clickedCircle, strokeTransition);
                //clickedCircle.setDisable(false);
            	//decide game
            }
          }
        };
    return coinMouseEventHandler;
  }

  private boolean checkMill(Board board, Position position, Paint color) {
    boolean millFormed = false;
    int horizontalMillDecider = 0;
    int verticalMillDecider = 0;
    boolean leftDecider = false;
    boolean rightDecider = false;
    boolean upDecider = false;
    boolean downDecider = false;
    if (position.getLeft() != null
        && position.getLeft().isFilled()
        && position.getLeft().getFill().equals(color)) {
      horizontalMillDecider++;
      leftDecider = true;
    }
    if (position.getUp() != null
        && position.getUp().isFilled()
        && position.getUp().getFill().equals(color)) {
      verticalMillDecider++;
      upDecider = true;
    }
    if (position.getRight() != null
        && position.getRight().isFilled()
        && position.getRight().getFill().equals(color)) {
      horizontalMillDecider++;
      rightDecider = true;
    }
    if (position.getDown() != null
        && position.getDown().isFilled()
        && position.getDown().getFill().equals(color)) {
      verticalMillDecider++;
      downDecider = true;
    }

    if (horizontalMillDecider == 1) {
      if (leftDecider) {
        if (position.getLeft().getLeft() != null
            && position.getLeft().getLeft().isFilled()
            && position.getLeft().getLeft().getFill().equals(color)) {
          horizontalMillDecider++;
        }
      } else if (rightDecider) {
        if (position.getRight().getRight() != null
            && position.getRight().getRight().isFilled()
            && position.getRight().getRight().getFill().equals(color)) {
          horizontalMillDecider++;
        }
      }

    } else if (verticalMillDecider == 1) {
      if (upDecider) {
        if (position.getUp().getUp() != null
            && position.getUp().getUp().isFilled()
            && position.getUp().getUp().getFill().equals(color)) {
          verticalMillDecider++;
        }
      } else if (downDecider) {
        if (position.getDown().getDown() != null
            && position.getDown().getDown().isFilled()
            && position.getDown().getDown().getFill().equals(color)) {
          verticalMillDecider++;
        }
      }
    }
    if (horizontalMillDecider == 2 || verticalMillDecider == 2) {
      millFormed = true;
    }
    System.out.println("Hor" + horizontalMillDecider);
    System.out.println("Ver" + verticalMillDecider);
    return millFormed;
  }
}
