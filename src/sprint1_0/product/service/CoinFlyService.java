package sprint1_0.product.service;

import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.shape.Circle;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;
import sprint1_0.product.model.PositionCircle;

public class CoinFlyService {

  public void prepareForCoinFlyMovement(Board board, String fromOp, int size) {
    System.out.println(fromOp);
    System.out.println(size);
    System.out.println(board.getDisplayTextTurn().getText());
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
    	System.out.println("Player 1 left out coins - "+ board.getPlayer1().getFilledPositions().size());
    	System.out.println("Player 2 left out coins - "+ board.getPlayer2().getFilledPositions().size());
    if (fromOp.equals(GameConstants.REMOVE)) {
      if (board.getPlayer1().getFilledPositions().size() == 3) {
        System.out.println("fly for player 1 from Remove");
        board.getAllPositionList().stream()
            .filter(pos -> !pos.isFilled())
            .forEach(e -> e.getPositionCircle().setDisable(true));
        board.getBlankPositionList().stream().forEach(e -> e.getPositionCircle().setDisable(true));
        enablePlayerFilledPositions(board, player1FilledPositions, false);
        enablePlayerFilledPositions(board, player2FilledPositions, true);
        board.setPhase3Started(true);
        board.setOp("FLY");
      } else if (board.getPlayer2().getFilledPositions().size() == 3) {
        System.out.println("fly for player 2");
        board.getAllPositionList().stream()
            .filter(pos -> !pos.isFilled())
            .forEach(e -> e.getPositionCircle().setDisable(true));
        board.getBlankPositionList().stream().forEach(e -> e.getPositionCircle().setDisable(true));
        enablePlayerFilledPositions(board, player2FilledPositions, false);
        enablePlayerFilledPositions(board, player1FilledPositions, true);
        board.setPhase3Started(true);
        board.setOp("FLY");
      }
    }
  }

  private void enablePlayerFilledPositions(
      Board board, List<Position> playerFilledPositions, boolean enableFlag) {
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
