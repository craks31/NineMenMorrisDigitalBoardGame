package sprint1_0.product.helper;

import javafx.scene.paint.Color;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.Board;

public class DisplayUtil {
  public void displayPlayerTurn(Board board, Color color) {
    if (color.equals(GameConstants.PLAYER1COLOR)) {
      board.getDisplayCircleTurn().setFill(color);
      board.getDisplayTextTurn().setText(GameConstants.PLAYER1TURNTEXT);
    } else {
      board.getDisplayCircleTurn().setFill(color);
      board.getDisplayTextTurn().setText(GameConstants.PLAYER2TURNTEXT);
    }
  }
}
