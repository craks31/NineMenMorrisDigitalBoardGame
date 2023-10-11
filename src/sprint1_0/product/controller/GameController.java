package sprint1_0.product.controller;

import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import sprint1_0.product.model.Board;
import sprint1_0.product.service.BoardInitService;
import sprint1_0.product.service.CoinPlacementService;

public class GameController {
	Board board;
	BoardInitService boardInitService = new BoardInitService();
	CoinPlacementService coinPlacementService = new CoinPlacementService();
	public void init(Board board) {
		this.board = board;
		boardInitService.setUpBoard(board);		
	}
	
public void startGame(Board board) {
	coinPlacementService.placeCoins(board);
}

  public Color decidePlayerTurn(Board board) {
	  return coinPlacementService.decidePlayerColorTurn(board);

  }
  
  public Board getNewBoard() {
	  return boardInitService.giveNewBoard();
  }
  
  public Board placeCoins(Board board, EventHandler eventHandler) {
	  return board;
  }
	
	
}
