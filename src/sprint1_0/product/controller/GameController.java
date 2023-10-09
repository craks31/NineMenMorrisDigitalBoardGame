package sprint1_0.product.controller;

import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.PositionCircle;
import sprint1_0.product.service.BoardInitService;

public class GameController {
	
	public void init(List<PositionCircle> positionCircleList, List<Circle> player1Coins, List<Circle> player2Coins) {
		BoardInitService boardInitService = new BoardInitService();
		startGame(boardInitService.setUpBoard(positionCircleList, player1Coins, player2Coins));
	}
	
	public void startGame(Board board) {}

  public Color decidePlayerTurn(List<Circle> player1Coins, List<Circle> player2Coins) {
    
    return Color.BLUEVIOLET;
  }
	
	
}
