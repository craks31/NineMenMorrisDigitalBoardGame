package sprint1_0.product.service;

import javafx.scene.paint.Color;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.Board;

public class GameEndService {
	public void prepareForGameEnd(Board board, Color playercolor) {
        board.getAllPositionList().stream() 
        .forEach(e -> e.getPositionCircle().setDisable(true)); 
        board.getBlankPositionList().stream()
                .forEach(e -> e.getPositionCircle().setDisable(true));
        if (playercolor.equals(GameConstants.PLAYER1COLOR)) {
            board.getDisplayCircleTurn().setFill(GameConstants.PLAYER2COLOR);
            board.getDisplayTextTurn().setText(GameConstants.PLAYER2WONTEXT);
          } else if(playercolor.equals(GameConstants.PLAYER2COLOR)){
            board.getDisplayCircleTurn().setFill(GameConstants.PLAYER1COLOR);
            board.getDisplayTextTurn().setText(GameConstants.PLAYER1WONTEXT);
          }
	}
	

}
