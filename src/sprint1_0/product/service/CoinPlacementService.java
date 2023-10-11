package sprint1_0.product.service;

import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.Board;

public class CoinPlacementService {

  public Color decidePlayerColorTurn(Board board) {
    Random rand = new Random();
    boolean isPlayer1 = rand.nextBoolean();
    if (isPlayer1) {
      return board.getPlayer1().getPlayerColor();
    } else {
      return board.getPlayer2().getPlayerColor();
    }
  }

  public void displayAndStartGameAfterDecide(Board board, Color decidedColor) {

    EventHandler<javafx.scene.input.MouseEvent> decideButtonEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {

          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            displayPlayerTurn(board, decidedColor);
            board.getDecideButton().setDisable(true);
            placeCoins(board);
          }
        };

    // Adding the event handler
    board.getDecideButton().setOnMouseClicked(decideButtonEventHandler);
  }

  public EventHandler<MouseEvent> placeCoins(Board board) {

    EventHandler<MouseEvent> coinFillerEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {

          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            if (!board.getPlayer1().getCoins().isEmpty()
                || !board.getPlayer2().getCoins().isEmpty()) {
              ((Circle) e.getSource()).setFill(board.getDisplayCircleTurn().getFill());

              if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER1COLOR)) {
                // IF PLAYER1 TURN
                displayPlayerTurn(board, GameConstants.PLAYER2COLOR);
                board.getPlayer1().getCoins().get(0).setFill( GameConstants.BACKGROUNDCOLOR);
                ;
                board.getPlayer1().getCoins().remove(0);
              } else {
                // IF PLAYER2 TURN
                displayPlayerTurn(board, GameConstants.PLAYER1COLOR);
                board.getPlayer2().getCoins().get(0).setFill( GameConstants.BACKGROUNDCOLOR);
                ;
                board.getPlayer2().getCoins().remove(0);
              }
              ((Circle) e.getSource()).setDisable(true);
            }
          }
        };

    for (int i = 0; i < board.getBlankPositionList().size(); i++) {
      // Adding the event handler
      board.getBlankPositionList().get(i).getPositionCircle().setDisable(false);
      board
          .getBlankPositionList()
          .get(i)
          .getPositionCircle()
          .addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, coinFillerEventHandler);
    }
    return coinFillerEventHandler;
  }

  public void displayPlayerTurn(Board board, Color color) {
    if (color.equals(GameConstants.PLAYER1COLOR)) {
      board.getDisplayCircleTurn().setFill(color);
      board.getDisplayTextTurn().setText("PLAYER 1'S TURN");
    } else {
      board.getDisplayCircleTurn().setFill(color);
      board.getDisplayTextTurn().setText("PLAYER 2'S TURN");
    }
  }
}
