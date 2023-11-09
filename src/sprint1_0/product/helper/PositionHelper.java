package sprint1_0.product.helper;

import java.util.List;
import java.util.stream.Collectors;

import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;

public class PositionHelper {
	  public static void disableEnablePositions(List<Position> positionsList, boolean enableFlag) {
		  positionsList.forEach(position -> position.getPositionCircle().setDisable(enableFlag));
	  }
	  public static void setPlayerPositionsList(Board board) {
		    List<Position> player1FilledPositions =
		        board.getAllPositionList().stream()
		            .filter(pos -> pos.isFilled() && pos.getFill().equals(GameConstants.PLAYER1COLOR))
		            .collect(Collectors.toList());
		    List<Position> player2FilledPositions =
		        board.getAllPositionList().stream()
		            .filter(pos -> pos.isFilled() && pos.getFill().equals(GameConstants.PLAYER2COLOR))
		            .collect(Collectors.toList());
		    board.getPlayer2().setFilledPositions(player2FilledPositions);
		    board.getPlayer1().setFilledPositions(player1FilledPositions);
	  }
}
