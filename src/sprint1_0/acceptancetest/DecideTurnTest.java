package sprint1_0.acceptancetest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.PositionCircle;
import sprint1_0.product.ui.GameManagerGUI;

/** @author rakesh */
public class DecideTurnTest extends ApplicationTest {

  private GameManagerGUI gameManagerGUI = new GameManagerGUI();

  private Parent rootNode;

  @Override
  public void start(Stage stage) throws Exception {
    gameManagerGUI.start(stage);
    stage.setScene(gameManagerGUI.getScene());
    stage.show();
    stage.toFront();
    rootNode = gameManagerGUI.getScene().getRoot();
  }

  @After
  public void tearDown() throws Exception {
    Button button = from(rootNode).lookup("#resetGameButton").query();
    clickOn(button);
  }

//  @Test
//  public void onClickofDecideGameButtonIsDecidedButtonDisabled() {
//    Button decideButton = from(rootNode).lookup("#decideButton").query();
//    clickOn(decideButton);
//    assertEquals(true, decideButton.isDisabled());
//  }

  // onclick text and circle change

  @Test
  public void checkifAllPositionsAreClickable() {
	    Button decideButton = from(rootNode).lookup("#decideButton").query();
	    clickOn(decideButton);
    for (int i = 0; i < GameConstants.NUM_POSITIONS_OF_BOARD; i++) {
      String pos = "#position" + String.valueOf(i + 1);
      PositionCircle c = from(rootNode).lookup(pos).query();
      assertEquals(false, c.isDisabled());
    }
  }
}
