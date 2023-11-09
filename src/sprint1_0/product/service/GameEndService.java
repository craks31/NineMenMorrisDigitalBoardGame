package sprint1_0.product.service;

import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.Board;

public class GameEndService {
  public void prepareForGameEnd(Board board) {
    board.getAllPositionList().stream().forEach(e -> e.getPositionCircle().setDisable(true));
    board.getBlankPositionList().stream().forEach(e -> e.getPositionCircle().setDisable(true));
    if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER1COLOR)
        && board.getPlayer1().getFilledPositions().size() == 3) {
    	board.getDisplayCircleTurn().setFill(GameConstants.PLAYER2COLOR);
      board.getDisplayTextTurn().setText(GameConstants.PLAYER2WONTEXT);
    } else if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER2COLOR)
        && board.getPlayer2().getFilledPositions().size() == 3) {
    	board.getDisplayCircleTurn().setFill(GameConstants.PLAYER1COLOR);
      board.getDisplayTextTurn().setText(GameConstants.PLAYER1WONTEXT);
    }
  }
}
