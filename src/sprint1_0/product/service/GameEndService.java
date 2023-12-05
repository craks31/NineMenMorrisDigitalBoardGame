package sprint1_0.product.service;

import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.Board;

public class GameEndService {
  public void prepareForGameEnd(Board board) {
    board.getAllPositionList().stream().forEach(e -> e.getPositionCircle().setDisable(true));
    board.getBlankPositionList().stream().forEach(e -> e.getPositionCircle().setDisable(true));
    if (board.getPlayer1().getFilledPositions().size() == 2) {
      board.getDisplayCircleTurn().setFill(GameConstants.PLAYER2COLOR);
      board.getDisplayTextTurn().setText(GameConstants.PLAYER2WONTEXT);
    } else if (board.getPlayer2().getFilledPositions().size() == 2) {
      board.getDisplayCircleTurn().setFill(GameConstants.PLAYER1COLOR);
      board.getDisplayTextTurn().setText(GameConstants.PLAYER1WONTEXT);
    }
    board.setOp("END");
  }
  
  public boolean decideGameEndCondition(Board board) {
	  boolean gameEnd = false;
	  if (board.isPhase3Started()
	            && ((board.getPlayer2().getFilledPositions().size() == 2)
	                || board.getPlayer1().getFilledPositions().size() == 2)) {
		  gameEnd = true;
	  }
	  return gameEnd;
  }
}
