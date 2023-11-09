package sprint1_0.product.service;

import java.util.List;
import java.util.stream.Collectors;

import javafx.animation.StrokeTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;
import sprint1_0.product.model.PositionCircle;

public class CoinMovementService {
  public void coinMoveEvent(Board board, Circle clickedCircle, StrokeTransition strokeTransition) {

    if (board.getPlayer1().getCoins().isEmpty() && board.getPlayer1().getCoins().isEmpty()) {

      Position clickedPosition = board.getAllPositionList().get((int) clickedCircle.getUserData());
      if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER1COLOR)) {
        // IF PLAYER1 TURN
        if (enableNeighbors(
            board, board.getAllPositionList().get((int) clickedCircle.getUserData()), strokeTransition)) {
        	strokeTransition.stop();
          clickedPosition.setFilled(false);
          clickedCircle.setFill(GameConstants.BACKGROUNDCOLOR);
          clickedPosition.setFill(null);
          board.getAllPositionList().stream()
              .filter(pos -> pos.isFilled())
              .forEach(pos -> pos.getPositionCircle().setDisable(true));
        }  
      } else {
        // IF PLAYER2 TURN
        if (enableNeighbors(
            board, board.getAllPositionList().get((int) clickedCircle.getUserData()), strokeTransition)) {
        	strokeTransition.stop();
          clickedPosition.setFilled(false);
          clickedCircle.setFill(GameConstants.BACKGROUNDCOLOR);
          clickedPosition.setFill(null);
          board.getAllPositionList().stream()
              .filter(pos -> pos.isFilled())
              .forEach(pos -> pos.getPositionCircle().setDisable(true));
        }
      }
    }
  }

  private boolean enableNeighbors(Board board, Position position, StrokeTransition strokeTransition) {
    if (position.getLeft() != null && !position.getLeft().isFilled()) {
      position.getLeft().getPositionCircle().setDisable(false);
      board.setOp("FILL");
      strokeTransition.setShape((Circle)position.getLeft().getPositionCircle());
      ((Circle)position.getLeft().getPositionCircle()).setStroke(Color.TRANSPARENT); // Make the stroke transparent
      ((Circle)position.getLeft().getPositionCircle()).setStrokeWidth(10);
      strokeTransition.play();
    }
    if (position.getUp() != null && !position.getUp().isFilled()) {
      position.getUp().getPositionCircle().setDisable(false);
      board.setOp("FILL");
      strokeTransition.play();
    }
    if (position.getRight() != null && !position.getRight().isFilled()) {
      position.getRight().getPositionCircle().setDisable(false);
      board.setOp("FILL");
      strokeTransition.play();
    }
    if (position.getDown() != null && !position.getDown().isFilled()) {
      position.getDown().getPositionCircle().setDisable(false);
      board.setOp("FILL");
      strokeTransition.play();
    }
    if (board.getOp().equals("FILL")) {
      return true;
    } else {
      return false;   
    }
  }

  public void prepareForCoinMovement(Board board) {
   // board.getBlankPositionList().forEach(e -> ((Circle) e).setDisable(true));
    List<Position> player1FilledPositions =
        board.getAllPositionList().stream()
            .filter(pos -> pos.isFilled() && pos.getFill().equals(GameConstants.PLAYER1COLOR))
            .collect(Collectors.toList());
    List<Position> player2FilledPositions =
        board.getAllPositionList().stream()
            .filter(pos -> pos.isFilled() && pos.getFill().equals(GameConstants.PLAYER2COLOR))
            .collect(Collectors.toList());
    board.getAllPositionList().stream() 
        .filter(pos -> !pos.isFilled())
        .forEach(e -> e.getPositionCircle().setDisable(true));
    board.getBlankPositionList().stream()
    .forEach(e -> e.getPositionCircle().setDisable(true));
    board.getPlayer2().setFilledPositions(player2FilledPositions);
    board.getPlayer1().setFilledPositions(player1FilledPositions);
    if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER1COLOR)) {
      enablePlayerFilledPositions(board, player1FilledPositions, false);
    } else {
      enablePlayerFilledPositions(board, player2FilledPositions, false);
    }
    board.setPhase2Started(true);
    board.setOp("MOVE");
  }

  private void enablePlayerFilledPositions(
      Board board, List<Position> playerFilledPositions, boolean enableFlag) {
    for (int i = 0; i < playerFilledPositions.size(); i++) {
      PositionCircle positionCircle = playerFilledPositions.get(i).getPositionCircle();
      positionCircle.setDisable(enableFlag);
    }
  }
}
