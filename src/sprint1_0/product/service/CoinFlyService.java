package sprint1_0.product.service;

import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;
import sprint1_0.product.model.PositionCircle;

public class CoinFlyService {
	
	public void prepareForCoinFlyMovement(Board board, Color playercolor) {
		// TODO Auto-generated method stub
		List<Position> player1FilledPositions = board.getAllPositionList().stream()
                .filter(pos -> pos.isFilled() && pos.getFill().equals(GameConstants.PLAYER1COLOR))
                .collect(Collectors.toList());
        List<Position> player2FilledPositions = board.getAllPositionList().stream()
                .filter(pos -> pos.isFilled() && pos.getFill().equals(GameConstants.PLAYER2COLOR))
                .collect(Collectors.toList());
        board.getAllPositionList().stream() 
        .filter(pos -> !pos.isFilled())
        .forEach(e -> e.getPositionCircle().setDisable(true)); 
        board.getBlankPositionList().stream()
                .forEach(e -> e.getPositionCircle().setDisable(true));

        board.getPlayer2().setFilledPositions(player2FilledPositions);
        board.getPlayer1().setFilledPositions(player1FilledPositions);

        if (playercolor.equals(GameConstants.PLAYER1COLOR)) { 
            enablePlayerFilledPositions(board, player1FilledPositions, false);
        } else  if (playercolor.equals(GameConstants.PLAYER2COLOR)) {
            enablePlayerFilledPositions(board, player2FilledPositions, false);
        }

        board.setOp("FLY");
    }

    private void enablePlayerFilledPositions(Board board, List<Position> playerFilledPositions, boolean enableFlag) {
        for (int i = 0; i < playerFilledPositions.size(); i++) {
            PositionCircle positionCircle = playerFilledPositions.get(i).getPositionCircle();
            positionCircle.setDisable(enableFlag);
        }
    }

	public void coinFlyEvent(Board board, Circle clickedCircle) {
	      Position clickedPosition = board.getAllPositionList().get((int) clickedCircle.getUserData());
	      clickedPosition.setFilled(false);
          clickedCircle.setFill(GameConstants.BACKGROUNDCOLOR);
          clickedPosition.setFill(null);
          board.getAllPositionList().stream()
              .filter(pos -> pos.isFilled())
              .forEach(pos -> pos.getPositionCircle().setDisable(true));
          board.getAllPositionList().stream() 
          .filter(pos -> !pos.isFilled())
          .forEach(e -> e.getPositionCircle().setDisable(false)); 
          board.setOp("FILL");


		
	}

	}


