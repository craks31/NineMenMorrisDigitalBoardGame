package sprint1_0.product.service;

import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.helper.PositionHelper;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;

public class CoinFlyService {
  CoinMillService coinMillService = new CoinMillService();

  public void prepareForCoinFlyMovement(Board board, Color playercolor) {
    List<Position> player1FilledPositions =
        PositionHelper.getPlayer1FilledCoins(board.getAllPositionList());

    List<Position> player2FilledPositions =
        PositionHelper.getPlayer2FilledCoins(board.getAllPositionList());

    board.getPlayer2().setFilledPositions(player2FilledPositions);
    board.getPlayer1().setFilledPositions(player1FilledPositions);
    PositionHelper.disableUnFilledPositions(board.getAllPositionList());
    board.getBlankPositionList().stream().forEach(e -> e.getPositionCircle().setDisable(true));
    if (playercolor.equals(GameConstants.PLAYER1COLOR)) {
      System.out.println("TRIGGERED FLY FOR PLAYER 1");
      PositionHelper.enableFilledPositions(player1FilledPositions);
      PositionHelper.disableFilledPositions(player2FilledPositions);
    } else if (playercolor.equals(GameConstants.PLAYER2COLOR)) {
      System.out.println("TRIGGERED FLY FOR PLAYER 2");
      PositionHelper.enableFilledPositions(player2FilledPositions);
      PositionHelper.disableFilledPositions(player1FilledPositions);
    }

    board.setPhase3Started(true);
    board.setOp("FLY");
    System.out.println("-------Board is set for Flying-------");
  }

  public void coinFlyEvent(Board board, Circle clickedCircle) {
    Position clickedPosition = board.getAllPositionList().get((int) clickedCircle.getUserData());
    clickedPosition.setFilled(false);
    clickedCircle.setFill(GameConstants.BACKGROUNDCOLOR);
    clickedPosition.setFill(null);
    coinMillService.breakTheMill(clickedPosition);
    PositionHelper.disableFilledPositions(board.getAllPositionList());
    PositionHelper.enableUnFilledPositions(board.getAllPositionList());
    clickedCircle.setDisable(true);
    board.setOp("FILL");
    System.out.println("-------Board is set for Fill AFTER Flying-------");
  }
}
