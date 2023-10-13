package sprint1_0.product.controller;

import javafx.scene.paint.Color;
import sprint1_0.product.model.Board;
import sprint1_0.product.service.BoardInitService;
import sprint1_0.product.service.CoinPlacementService;

/**
 * @author rakesh
 *
 */
public class GameController {
  Board board;
  BoardInitService boardInitService = new BoardInitService();
  CoinPlacementService coinPlacementService = new CoinPlacementService();

  public void init(Board board) {
    this.board = board;
    boardInitService.setUpBoard(board);
  }
  
  public Board getExistingBoard() {
	  return board;
  }
  

  public void startGame(Board board, Color decideColor) {
    coinPlacementService.displayAndStartGameAfterDecide(board, decideColor);
  }

  public Color decidePlayerTurn(Board board) {
    return coinPlacementService.decidePlayerColorTurn(board);
  }

  public Board getNewBoard() {
    return boardInitService.giveNewBoard();
  }

  public void resetGame(Board board) {
    boardInitService.resetGame(board);
  }

  public void newGame(Board board) {
    boardInitService.newGame(board);
  }
}
