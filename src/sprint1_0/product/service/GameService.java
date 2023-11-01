package sprint1_0.product.service;

import javafx.scene.paint.Color;
import sprint1_0.product.model.Board;

public class GameService {

  private boolean gameOver = false;

  private boolean phase1Over = false;

  private boolean phase2Over = false;

  CoinPlacementService coinPlacementService = new CoinPlacementService();
  //CoinMovementService coinMovementService = new CoinMovementService();

  public void startGame(Board board, Color decideColor) {
    //    if (!phase1Over) {
    coinPlacementService.placeCoinsAndDisplayInit(board, decideColor);
    //      if (board.getPlayer1().getCoins().isEmpty() && board.getPlayer1().getCoins().isEmpty())
    //        phase1Over = true;
    //    }
    //    while (!phase2Over) {
    //      coinMovementService.makeMove(board);
    //      phase2Over = true;
    //    }
    //    while (!gameOver) {
    //      displayInit();
    //    }
  }
}
