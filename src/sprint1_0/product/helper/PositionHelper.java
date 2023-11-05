package sprint1_0.product.helper;

import java.util.List;

import sprint1_0.product.model.Position;

public class PositionHelper {
	  public static void disableEnablePositions(List<Position> positionsList, boolean enableFlag) {
		  positionsList.forEach(position -> position.getPositionCircle().setDisable(enableFlag));
	  }
}
