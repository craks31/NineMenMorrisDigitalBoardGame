package sprint1_0.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javafx.scene.shape.Circle;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.helper.PositionHelper;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;

public class CoinMovementService {
  CoinMillService coinMillService = new CoinMillService();
  ComputerGamePlayService compGamePlayService = new ComputerGamePlayService();

  public void coinMoveEvent(Board board, Circle clickedCircle, ExecutorService executorService) {

    if (board.getPlayer1().getCoins().isEmpty() && board.getPlayer2().getCoins().isEmpty()) {

      Position clickedPosition = board.getAllPositionList().get((int) clickedCircle.getUserData());
      if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER1COLOR)) {
        // IF PLAYER1 TURN
        if (enableNeighbors(
            board, board.getAllPositionList().get((int) clickedCircle.getUserData()))) {
          System.out.println("PLAYER 1 TURN");
          PositionHelper.gameHistoryPrinterForPlayer1(clickedPosition, board.getOp());
          clickedPosition.setFilled(false);
          clickedCircle.setFill(GameConstants.BACKGROUNDCOLOR);
          clickedPosition.setFill(null);
          coinMillService.breakTheMill(clickedPosition);
          board.getAllPositionList().stream()
              .filter(pos -> pos.isFilled())
              .forEach(pos -> pos.getPositionCircle().setDisable(true));
        }
      } else {
        // IF PLAYER2 TURN
        if (enableNeighbors(
            board, board.getAllPositionList().get((int) clickedCircle.getUserData()))) {
          System.out.println("PLAYER 2 TURN");
          PositionHelper.gameHistoryPrinterForPlayer2(clickedPosition, "MOVE");
          clickedPosition.setFilled(false);
          clickedCircle.setFill(GameConstants.BACKGROUNDCOLOR);
          clickedPosition.setFill(null);
          coinMillService.breakTheMill(clickedPosition);
          board.getAllPositionList().stream()
              .filter(pos -> pos.isFilled())
              .forEach(pos -> pos.getPositionCircle().setDisable(true));
        }
      }
    }
  }

  private boolean enableNeighbors(Board board, Position position) {
    List<Position> emptyNeighbors = new ArrayList<>();
    if (position.getLeft() != null && !position.getLeft().isFilled()) {
      position.getLeft().getPositionCircle().setDisable(false);
      board.setOp("FILL");
      emptyNeighbors.add(position.getLeft());
      System.out.println("-------LEFT Neighbor READY For Fill-------");
    }
    if (position.getUp() != null && !position.getUp().isFilled()) {
      position.getUp().getPositionCircle().setDisable(false);
      board.setOp("FILL");
      emptyNeighbors.add(position.getUp());
      System.out.println("-------UP Neighbor READY For Fill-------");
    }
    if (position.getRight() != null && !position.getRight().isFilled()) {
      position.getRight().getPositionCircle().setDisable(false);
      board.setOp("FILL");
      emptyNeighbors.add(position.getRight());
      System.out.println("-------RIGHT Neighbor READY For Fill-------");
    }
    if (position.getDown() != null && !position.getDown().isFilled()) {
      position.getDown().getPositionCircle().setDisable(false);
      board.setOp("FILL");
      emptyNeighbors.add(position.getDown());
      System.out.println("-------DOWN Neighbor READY For Fill-------");
    }
    if (board.getOp().equals("FILL")) {
      board.setEmptyNeighborsForComputer(emptyNeighbors);
      return true;
    } else {
      return false;
    }
  }

  public void prepareForCoinMovement(Board board, ExecutorService executorService) {
    List<Position> player1FilledPositions =
        PositionHelper.getPlayer1FilledCoins(board.getAllPositionList());

    List<Position> player2FilledPositions =
        PositionHelper.getPlayer2FilledCoins(board.getAllPositionList());

    board.getAllPositionList().stream()
        .filter(pos -> !pos.isFilled())
        .forEach(e -> e.getPositionCircle().setDisable(true));
    board.getBlankPositionList().stream().forEach(e -> e.getPositionCircle().setDisable(true));
    board.getPlayer2().setFilledPositions(player2FilledPositions);
    board.getPlayer1().setFilledPositions(player1FilledPositions);
    if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER1COLOR)) {
      PositionHelper.enablePositions(player1FilledPositions);
      PositionHelper.disablePositions(player2FilledPositions);
      board.setPhase2Started(true);
      board.setOp("MOVE");
      System.out.println("-------Board is set for Move For Player 1-------");
    } else {
      PositionHelper.enablePositions(player2FilledPositions);
      PositionHelper.disablePositions(player1FilledPositions);
      board.setPhase2Started(true);
      board.setOp("MOVE");
      System.out.println("-------Board is set for Move For Player 2-------");
    }
  }

  public List<Position> findComputerCoinsWithEmptyNeighbors(
      Board board, List<Position> positionList) {
    List<Position> computerCoinsWithEmptyNeighbors = new ArrayList<>();
    for (Position p : positionList) {
  	  board.setOp("MOVE");
      if (enableNeighbors(board, p)) {
        computerCoinsWithEmptyNeighbors.add(p);
      }
    }
    System.out.println(
        "Player 2 Coins Ready For Movemement : " + computerCoinsWithEmptyNeighbors.size());
    board.setOp("MOVE");
    return computerCoinsWithEmptyNeighbors;
  }
}
