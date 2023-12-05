package sprint1_0.product.helper;

import java.util.List;
import java.util.stream.Collectors;

import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;

public class PositionHelper {

  public static void enablePositions(List<Position> positionsList) {
    positionsList.forEach(position -> position.getPositionCircle().setDisable(false));
  }

  public static void disablePositions(List<Position> positionsList) {
    positionsList.forEach(position -> position.getPositionCircle().setDisable(true));
  }

  public static void enableFilledPositions(List<Position> positionsList) {
    positionsList.stream()
        .filter(pos -> pos.isFilled())
        .forEach(pos -> pos.getPositionCircle().setDisable(false));
  }

  public static void disableFilledPositions(List<Position> positionsList) {
    positionsList.stream()
        .filter(pos -> pos.isFilled())
        .forEach(pos -> pos.getPositionCircle().setDisable(true));
  }

  public static void enableUnFilledPositions(List<Position> positionsList) {
    positionsList.stream()
        .filter(pos -> !pos.isFilled())
        .forEach(pos -> pos.getPositionCircle().setDisable(false));
  }

  public static void disableUnFilledPositions(List<Position> positionsList) {
    positionsList.stream()
        .filter(pos -> !pos.isFilled())
        .forEach(pos -> pos.getPositionCircle().setDisable(true));
  }

  public static List<Position> getPlayer1FilledCoins(List<Position> allPositionList) {
    return allPositionList.stream()
        .filter(pos -> pos.isFilled() && pos.getFill().equals(GameConstants.PLAYER1COLOR))
        .collect(Collectors.toList());
  }

  public static List<Position> getPlayer2FilledCoins(List<Position> allPositionList) {
    return allPositionList.stream()
        .filter(pos -> pos.isFilled() && pos.getFill().equals(GameConstants.PLAYER2COLOR))
        .collect(Collectors.toList());
  }

  public static void setPlayerPositionsList(Board board) {
    List<Position> player1FilledPositions =
        board.getAllPositionList().stream()
            .filter(pos -> pos.isFilled() && pos.getFill().equals(GameConstants.PLAYER1COLOR))
            .collect(Collectors.toList());
    List<Position> player2FilledPositions =
        board.getAllPositionList().stream()
            .filter(pos -> pos.isFilled() && pos.getFill().equals(GameConstants.PLAYER2COLOR))
            .collect(Collectors.toList());
    board.getPlayer2().setFilledPositions(player2FilledPositions);
    board.getPlayer1().setFilledPositions(player1FilledPositions);
  }
  
  public static void gameHistoryPrinterForPlayer1(Position p, String op) {
	  System.out.println("GAME HISTORY : Player 1 coin " + op +" : Position " + p.getPositionId());
  }
  public static void gameHistoryPrinterForPlayer2(Position p, String op) {
	  System.out.println("GAME HISTORY : Player 2 coin " + op +" : Position " + p.getPositionId());
  }
}
