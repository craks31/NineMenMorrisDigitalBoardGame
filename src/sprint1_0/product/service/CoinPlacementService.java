package sprint1_0.product.service;

import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.Board;

public class CoinPlacementService {
	
public Color decidePlayerColorTurn(Board board) {
    Random rand = new Random();
    int max = 2;
    int min = 1;
    int num = rand.nextInt((max-min+1)+min);
    if(num==1){
    	return board.getPlayer1().getPlayerColor();
    }
    else {
    	return board.getPlayer2().getPlayerColor();
    }
}

public void placeCoins(Board board) {
	
    EventHandler<javafx.scene.input.MouseEvent> coinFillerEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {

          @Override
          public void handle(javafx.scene.input.MouseEvent e) {

            ((Circle) e.getSource()).setFill(board.getDisplayCircleTurn().getFill());
            if (board.getDisplayCircleTurn().getFill().equals(GameConstants.player1Color)) {
              board.getDisplayCircleTurn().setFill(GameConstants.player2Color);
              displayPlayerTurn(board, GameConstants.player2Color);
            }
            else {
              board.getDisplayCircleTurn().setFill(GameConstants.player1Color);
              displayPlayerTurn(board, GameConstants.player1Color);
            }
            
          }
        };
    for (int i = 0; i < board.getBlankPositionList().size(); i++) {
      // Adding the event handler
      board
          .getBlankPositionList()
          .get(i)
          .getPositionCircle()
          .addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, coinFillerEventHandler);
    }
    
    

}
public void displayPlayerTurn(Board board, Color color) {
    if (color.equals(GameConstants.player1Color)) {
      board.getDisplayCircleTurn().setFill(color);
      board.getDisplayTextTurn().setText("PLAYER 1'S TURN");
    } else {
      board.getDisplayCircleTurn().setFill(color);
      board.getDisplayTextTurn().setText("PLAYER 2'S TURN");
    }
  }

}
