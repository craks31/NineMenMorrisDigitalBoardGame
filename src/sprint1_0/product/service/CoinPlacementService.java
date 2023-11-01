package sprint1_0.product.service;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.helper.DisplayUtil;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;
import sprint1_0.product.model.PositionCircle;

/** @author rakesh pujitha rekha */
public class CoinPlacementService {

  DisplayUtil displayUtil = new DisplayUtil();

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

  public void placeCoinsAndDisplayInit(Board board, Color decidedColor) {

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

  public void placeCoinsSetup(Board board) {

    for (int i = 0; i < board.getBlankPositionList().size(); i++) {
      PositionCircle positionCircle = board.getBlankPositionList().get(i).getPositionCircle();
      // Adding the event handler
      positionCircle.setDisable(false);
      positionCircle.addEventHandler(
          javafx.scene.input.MouseEvent.MOUSE_CLICKED, placerAndFillerEventHandler(board));
      positionCircle.setUserData(i);
    }
  }

  public EventHandler<MouseEvent> placerAndFillerEventHandler(Board board) {
    EventHandler<MouseEvent> coinFillerEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {

          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            Circle clickedCircle = (Circle) e.getSource();
            if (!board.getPlayer1().getCoins().isEmpty()
                || !board.getPlayer2().getCoins().isEmpty()) {
              clickedCircle.setFill(board.getDisplayCircleTurn().getFill());
              Position clickedPosition =
                  board.getFilledPositionList().get((int) clickedCircle.getUserData());
              clickedPosition.setFilled(true);
              clickedPosition.setFill(board.getDisplayCircleTurn().getFill());
              if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER1COLOR)) {
                // IF PLAYER1 TURN
                displayUtil.displayPlayerTurn(board, GameConstants.PLAYER2COLOR);
                board.getPlayer1().getCoins().get(0).setFill(GameConstants.BACKGROUNDCOLOR);
                board.getPlayer1().getCoins().remove(0);
              } else {
                // IF PLAYER2 TURN
                displayUtil.displayPlayerTurn(board, GameConstants.PLAYER1COLOR);
                board.getPlayer2().getCoins().get(0).setFill(GameConstants.BACKGROUNDCOLOR);
                board.getPlayer2().getCoins().remove(0);
                //                Position p = new Position();
                //                p.setPositionCircle((PositionCircle) clickedCircle);
                //                board.getPlayer2().getFilledPositions().add(p);
              }
              if (board.getPlayer1().getCoins().isEmpty()
                  && board.getPlayer2().getCoins().isEmpty()) {
                // initiatePhase2
                makeMove(board);
              }

              ((Circle) e.getSource()).setDisable(true);
            }
          }
        };
    return coinFillerEventHandler;
  }

  public void makeMove(Board board) {
    List<Position> player1FilledPositions =
        board.getFilledPositionList().stream()
            .filter(pos -> pos.isFilled()&&pos.getFill().equals(GameConstants.PLAYER1COLOR))
            .collect(Collectors.toList());
    List<Position> player2FilledPositions =
        board.getFilledPositionList().stream()
            .filter(pos -> pos.getFill()!=null&&pos.getFill().equals(GameConstants.PLAYER2COLOR))
            .collect(Collectors.toList());
    board.getPlayer2().setFilledPositions(player2FilledPositions);
    board.getPlayer1().setFilledPositions(player1FilledPositions);
    if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER1COLOR)) {
      enablePlayerFilledPositions(board, player1FilledPositions, false);
    } else {
      enablePlayerFilledPositions(board, player2FilledPositions, false);
    }
  }

  public EventHandler<MouseEvent> moveEventHandler(Board board) {
    EventHandler<MouseEvent> coinMoverAndFillerEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {

          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            if (board.getPlayer1().getCoins().isEmpty()
                && board.getPlayer1().getCoins().isEmpty()) {
              Circle clickedCircle = (Circle) e.getSource();

              if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER1COLOR)) {
                // IF PLAYER1 TURN
                enableNeighbors(
                    board, board.getFilledPositionList().get((int) clickedCircle.getUserData()));
                enablePlayerFilledPositions(board, board.getPlayer1().getFilledPositions(), true);
                enablePlayerFilledPositions(board, board.getPlayer2().getFilledPositions(), false);
              } else {
                // IF PLAYER2 TURN
                enableNeighbors(
                    board, board.getFilledPositionList().get((int) clickedCircle.getUserData()));
                enablePlayerFilledPositions(board, board.getPlayer1().getFilledPositions(), false);
                enablePlayerFilledPositions(board, board.getPlayer2().getFilledPositions(), true);
              }
            }
          }
        };

    return coinMoverAndFillerEventHandler;
  }

  private void enableNeighbors(Board board, Position position) {
    if (position.getLeft() != null && !position.getLeft().isFilled()) {
      position.getLeft().setDisable(false);
      position.getLeft().setOnMouseClicked(placerAndFillerEventHandler(board));
    } else if (position.getUp() != null && !position.getUp().isFilled()) {
      position.getUp().setDisable(false);
      position.getUp().setOnMouseClicked(placerAndFillerEventHandler(board));
    } else if (position.getRight() != null && !position.getRight().isFilled()) {
      position.getRight().setDisable(false);
      position.getRight().setOnMouseClicked(placerAndFillerEventHandler(board));
    } else if (position.getDown() != null && !position.getDown().isFilled()) {
      position.getDown().setDisable(false);
      position.getDown().setOnMouseClicked(placerAndFillerEventHandler(board));
    }
  }

  private void enablePlayerFilledPositions(
      Board board, List<Position> playerFilledPositions, boolean enableFlag) {
    for (int i = 0; i < playerFilledPositions.size(); i++) {
      PositionCircle positionCircle = playerFilledPositions.get(i).getPositionCircle();
      positionCircle.addEventHandler(MouseEvent.MOUSE_CLICKED, moveEventHandler(board));
      positionCircle.setDisable(enableFlag);
    }
  }
}
