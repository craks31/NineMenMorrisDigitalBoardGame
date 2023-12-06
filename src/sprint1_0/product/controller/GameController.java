package sprint1_0.product.controller;

import java.util.concurrent.ExecutorService;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.Board;
import sprint1_0.product.service.BoardInitService;
import sprint1_0.product.service.GameService;

/** @author rakesh rekha ravali pujitha*/
public class GameController {
  Board board;
  BoardInitService boardInitService = new BoardInitService();
 
  GameService gameService = new GameService();

  public void init(Board board, String variant) {
    this.board = board;
    if(variant.equals(GameConstants.NINEVARIANT))
    	boardInitService.setUpNineBoard(board);
    else if(variant.equals(GameConstants.SIXVARIANT))
    	boardInitService.setUpSixBoard(board);
  }

  public Board getExistingBoard() {
    return board;
  }

  public void startGame(Board board, Color decideColor, ExecutorService executorService, Stage stage) {
	  gameService.startGame(board, decideColor, executorService, stage);
  }
  
  public Color decidePlayerTurn(Board board) {
    return gameService.decidePlayerColorTurn(board);
  }

  public Board getNewBoard() {
    return boardInitService.giveNewBoard();
  }

  public void resetGame(Board board) {
    boardInitService.resetGame(board);
  }

  public void newGame(Board board, Stage primaryStage) {
    boardInitService.newGame(board, primaryStage);
  }
  
}
