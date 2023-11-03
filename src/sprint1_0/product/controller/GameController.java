package sprint1_0.product.controller;

import javafx.scene.paint.Color;
import sprint1_0.product.model.Board;
import sprint1_0.product.service.BoardInitService;
import sprint1_0.product.service.GameService;

/** @author rakesh rekha ravali pujitha*/
public class GameController {
  Board board;
  BoardInitService boardInitService = new BoardInitService();
 
  GameService gameService = new GameService();

  public void init(Board board) {
    this.board = board;
    boardInitService.setUpBoard(board);
  }

  public Board getExistingBoard() {
    return board;
  }

  public void startGame(Board board, Color decideColor) {
	  gameService.startGame(board, decideColor);
  }

  public void startPhase1Game(Board board, Color decideColor) {
	  gameService.startGame(board, decideColor);
  }

  public void startPhase2Game(Board board) {
	 // coinMovementService.makeMove(board);
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

  public void newGame(Board board) {
    boardInitService.newGame(board);
  }
  
}
