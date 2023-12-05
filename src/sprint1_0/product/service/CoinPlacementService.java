package sprint1_0.product.service;

import java.util.concurrent.ExecutorService;

import javafx.scene.shape.Circle;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.helper.DisplayUtil;
import sprint1_0.product.helper.PositionHelper;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;

/** @author rakesh pujitha rekha */
public class CoinPlacementService {

  CoinMovementService coinMovementService = new CoinMovementService();
  CoinFlyService coinFlyService = new CoinFlyService();
  DisplayUtil displayUtil = new DisplayUtil();
  ComputerGamePlayService compGamePlayService = new ComputerGamePlayService();

  public void coinFillEvent(Board board, Circle clickedCircle, ExecutorService executorService) {

    Position clickedPosition = board.getAllPositionList().get((int) clickedCircle.getUserData());

    clickedCircle.setFill(board.getDisplayCircleTurn().getFill());
    board.getAllPositionList().get((int) clickedCircle.getUserData()).setFilled(true);
    board
        .getAllPositionList()
        .get((int) clickedCircle.getUserData())
        .setFill(board.getDisplayCircleTurn().getFill());
    clickedCircle.setDisable(true);

    if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER1COLOR)) {
      // IF PLAYER1 TURN
      System.out.println("PLAYER 1 TURN");
      PositionHelper.gameHistoryPrinterForPlayer1(clickedPosition, board.getOp());

      displayUtil.displayPlayerTurn(board, GameConstants.PLAYER2COLOR);

      if (!board.getPlayer1().getCoins().isEmpty()) {
        board.getPlayer1().getCoins().get(0).setFill(GameConstants.BACKGROUNDCOLOR);
        board.getPlayer1().getCoins().remove(0);
      }

      if (board.isPhase2Started() && board.getPlayer2().getFilledPositions().size() == 3) {
        board.setPhase3Started(true);
        coinFlyService.prepareForCoinFlyMovement(board, GameConstants.PLAYER2COLOR);
      } else if (board.getPlayer1().getCoins().isEmpty()
          && board.getPlayer2().getCoins().isEmpty()) {
        coinMovementService.prepareForCoinMovement(board, executorService);
      }

    } else {
      // IF PLAYER2 TURN
      System.out.println("PLAYER 2 TURN");
      PositionHelper.gameHistoryPrinterForPlayer2(clickedPosition, board.getOp());
      displayUtil.displayPlayerTurn(board, GameConstants.PLAYER1COLOR);
      if (!board.getPlayer2().getCoins().isEmpty()) {
        board.getPlayer2().getCoins().get(0).setFill(GameConstants.BACKGROUNDCOLOR);
        board.getPlayer2().getCoins().remove(0);
      }
      if (board.isPhase2Started() && board.getPlayer1().getFilledPositions().size() == 3) {
        board.setPhase3Started(true);
        coinFlyService.prepareForCoinFlyMovement(board, GameConstants.PLAYER1COLOR);
      } else if (board.getPlayer1().getCoins().isEmpty()
          && board.getPlayer2().getCoins().isEmpty()) {
        // initiatePhase2
        coinMovementService.prepareForCoinMovement(board, executorService);
      }
    }

    board.getBlankPositionList().remove(clickedPosition);
  }
}
