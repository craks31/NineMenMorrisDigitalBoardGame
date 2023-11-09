package sprint1_0.product.service;

import java.util.List;
import java.util.stream.Collectors;

import javafx.animation.StrokeTransition;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.helper.DisplayUtil;
import sprint1_0.product.helper.PositionHelper;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;

public class CoinRemovalService {
  DisplayUtil displayUtil = new DisplayUtil();
  CoinFlyService coinFlyService = new CoinFlyService();
  GameEndService gameEndService = new GameEndService();

  public void coinRemoveEvent(
      Board board, Circle clickedCircle, StrokeTransition strokeTransition) {
    clickedCircle.setFill(GameConstants.BACKGROUNDCOLOR);
    strokeTransition.stop();
    Position clickedPosition = board.getAllPositionList().get((int) clickedCircle.getUserData());
    clickedPosition.setFilled(false);
    if (!board.isPhase2Started()) {
      board.getAllPositionList().stream()
          .filter(pos -> pos.isFilled())
          .forEach(e -> e.getPositionCircle().setDisable(true));
      board.getAllPositionList().stream()
          .filter(pos -> !pos.isFilled())
          .forEach(e -> e.getPositionCircle().setDisable(false));
      board.setOp("FILL");
      if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER1COLOR)) {
        displayUtil.displayPlayerTurn(board, GameConstants.PLAYER2COLOR);
      } else if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER2COLOR)) {
        displayUtil.displayPlayerTurn(board, GameConstants.PLAYER1COLOR);
      }
    } else if (board.isPhase2Started()) {
      if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER1COLOR)) {
        board.getPlayer1().getFilledPositions().stream()
            .filter(pos -> pos.isFilled())
            .forEach(e -> e.getPositionCircle().setDisable(true));
        board.getPlayer2().getFilledPositions().stream()
            .filter(pos -> pos.isFilled())
            .forEach(e -> e.getPositionCircle().setDisable(false));
        PositionHelper.setPlayerPositionsList(board);
        System.out.println(
            "Player 1 left out coins - " + board.getPlayer1().getFilledPositions().size());
        System.out.println(
            "Player 2 left out coins - " + board.getPlayer2().getFilledPositions().size());

        if (board.getPlayer2().getFilledPositions().size() == 3) {
          coinFlyService.prepareForCoinFlyMovement(board, GameConstants.PLAYER2COLOR);
          displayUtil.displayPlayerTurn(board, GameConstants.PLAYER2COLOR);
        }
        if (board.isPhase3Started()
            && ((board.getPlayer2().getFilledPositions().size() == 2)
                || board.getPlayer1().getFilledPositions().size() == 2)) {
          System.out.println("END TRIGGERED");
          gameEndService.prepareForGameEnd(board);
        }
        if (board.getOp().equals("REMOVE")) {
          board.setOp("MOVE");
          displayUtil.displayPlayerTurn(board, GameConstants.PLAYER2COLOR);
        }

      } else if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER2COLOR)) {
        board.getPlayer1().getFilledPositions().stream()
            .filter(pos -> pos.isFilled())
            .forEach(e -> e.getPositionCircle().setDisable(false));
        board.getPlayer2().getFilledPositions().stream()
            .filter(pos -> pos.isFilled())
            .forEach(e -> e.getPositionCircle().setDisable(true));
        PositionHelper.setPlayerPositionsList(board);
        System.out.println(
            "Player 1 left out coins - " + board.getPlayer1().getFilledPositions().size());
        System.out.println(
            "Player 2 left out coins - " + board.getPlayer2().getFilledPositions().size());

        if (board.getPlayer1().getFilledPositions().size() == 3) {
          coinFlyService.prepareForCoinFlyMovement(board, GameConstants.PLAYER1COLOR);
          displayUtil.displayPlayerTurn(board, GameConstants.PLAYER1COLOR);
        }
        if (board.isPhase3Started()
            && ((board.getPlayer2().getFilledPositions().size() == 2)
                || board.getPlayer1().getFilledPositions().size() == 2)) {
          System.out.println("END TRIGGERED");
          gameEndService.prepareForGameEnd(board);
        }
        if (board.getOp().equals("REMOVE")) {
          board.setOp("MOVE");
          displayUtil.displayPlayerTurn(board, GameConstants.PLAYER1COLOR);
        }
      }
    }

    board.getBlankPositionList().add(clickedPosition);
  }

  public void prepareForCoinRemoval(Board board, Paint color) {
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
    board.getPlayer2().setFilledPositions(player2FilledPositions);
    board.getPlayer1().setFilledPositions(player1FilledPositions);
    if (color.equals(GameConstants.PLAYER1COLOR)) {
      displayUtil.displayPlayerTurn(board, GameConstants.PLAYER1COLOR);
      PositionHelper.disableEnablePositions(player2FilledPositions, false);
    } else {
      displayUtil.displayPlayerTurn(board, GameConstants.PLAYER2COLOR);
      PositionHelper.disableEnablePositions(player1FilledPositions, false);
    }
    board.setOp("REMOVE");
  }
}
