package sprint1_0.product.service;

import javafx.scene.shape.Circle;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.helper.DisplayUtil;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;

/** @author rakesh pujitha rekha */
public class CoinPlacementService {
  CoinMovementService coinMovementService = new CoinMovementService();
  CoinFlyService coinFlyService = new CoinFlyService();
  DisplayUtil displayUtil = new DisplayUtil();

  public void coinFillEvent(Board board, Circle clickedCircle) {
    clickedCircle.setFill(board.getDisplayCircleTurn().getFill());
    Position clickedPosition = board.getAllPositionList().get((int) clickedCircle.getUserData());
    clickedPosition.setFilled(true);
    clickedPosition.setFill(board.getDisplayCircleTurn().getFill());
    if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER1COLOR)) {
      // IF PLAYER1 TURN
      displayUtil.displayPlayerTurn(board, GameConstants.PLAYER2COLOR);
      if (!board.getPlayer1().getCoins().isEmpty()) {
        board.getPlayer1().getCoins().get(0).setFill(GameConstants.BACKGROUNDCOLOR);
        board.getPlayer1().getCoins().remove(0);
      }
    } else {
      // IF PLAYER2 TURN
      displayUtil.displayPlayerTurn(board, GameConstants.PLAYER1COLOR);
      if (!board.getPlayer2().getCoins().isEmpty()) {
        board.getPlayer2().getCoins().get(0).setFill(GameConstants.BACKGROUNDCOLOR);
        board.getPlayer2().getCoins().remove(0);
      }
    }
    if (board.getPlayer1().getCoins().isEmpty() && board.getPlayer2().getCoins().isEmpty()) {
      // initiatePhase2
      coinMovementService.prepareForCoinMovement(board);
    }
//    if (board.isPhase2Started()
//        && (board.getPlayer1().getFilledPositions().size() == 3
//            || board.getPlayer2().getFilledPositions().size() == 3)) {
//    	System.out.println("Fly from Placement" ); 
//      coinFlyService.prepareForCoinFlyMovement(board, GameConstants.FILL, 3);
//    }
    board.getBlankPositionList().remove(clickedPosition);
  }
}
