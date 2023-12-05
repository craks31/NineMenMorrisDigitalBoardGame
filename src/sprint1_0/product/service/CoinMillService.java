package sprint1_0.product.service;

import java.util.ArrayList;
import java.util.List;

import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;

public class CoinMillService {

  public boolean enableNonMillOpponentCoinsToRemove(Board board, List<Position> positionsList) {
    List<Position> nonMillCoinsForComputer = new ArrayList<>();
    boolean nonMillPresent = false;
    for (Position position : positionsList) {
      if (position.isPartOfHorMill()) {
        position.getPositionCircle().setDisable(true);
      } else if (position.isPartOfVerMill()) {
        position.getPositionCircle().setDisable(true);
      } else {
        position.getPositionCircle().setDisable(false);
        nonMillPresent = true;
        System.out.println("Non Mill Opponent Coins Are Present");
        board.setOp("REMOVE");
        nonMillCoinsForComputer.add(position);
      }
    }
    board.setNonMillCoinsForComputer(nonMillCoinsForComputer);
    return nonMillPresent;
  }

  public void breakTheMill(Position clickedPosition) {

    if (clickedPosition.isPartOfHorMill() && clickedPosition.getHorMillFamily() != null) {
      clickedPosition.getHorMillFamily().forEach(p -> p.setPartOfHorMill(false));
    }
    if (clickedPosition.isPartOfVerMill() && clickedPosition.getVerMillFamily() != null) {
      clickedPosition.getVerMillFamily().forEach(p -> p.setPartOfVerMill(false));
    }
  }
}
