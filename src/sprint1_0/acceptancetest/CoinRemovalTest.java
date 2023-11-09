package sprint1_0.acceptancetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.Position;
import sprint1_0.product.model.PositionCircle;
import sprint1_0.product.ui.GameManagerGUI;

public class CoinRemovalTest extends ApplicationTest {
  private GameManagerGUI gameManagerGUI = new GameManagerGUI();

  private Parent rootNode;

  @Override
  public void start(Stage stage) throws Exception {
    gameManagerGUI.start(stage);
    stage.setScene(gameManagerGUI.getScene());
    stage.show();
    stage.toFront();
    rootNode = gameManagerGUI.getScene().getRoot();
    Button newGameButton = from(rootNode).lookup("#newGameButton").query();
    Button decideButton = from(rootNode).lookup("#decideButton").query();
    clickOn(newGameButton);
    clickOn(decideButton);
  }

  @After
  public void tearDown() throws Exception {
    Button button = from(rootNode).lookup("#resetGameButton").query();
    clickOn(button);
  }

  @Test
  public void checkHorizantalMillFormation() {
    // 1,4,6 is the horizontal Mill
    PositionCircle player1PositionCircle1 = from(rootNode).lookup("#position1").query();
    clickOn(player1PositionCircle1);
    PositionCircle player2PositionCircle1 = from(rootNode).lookup("#position2").query();
    clickOn(player2PositionCircle1);
    PositionCircle player1PositionCircle2 = from(rootNode).lookup("#position4").query();
    clickOn(player1PositionCircle2);
    PositionCircle player2PositionCircle2 = from(rootNode).lookup("#position3").query();
    clickOn(player2PositionCircle2);
    PositionCircle player1PositionCircle3 = from(rootNode).lookup("#position6").query();
    clickOn(player1PositionCircle3);

    assertEquals(GameConstants.REMOVE, gameManagerGUI.getBoard().getOp());
  }

  @Test
  public void checkVerticalMillFormation() {
    // 1,2,3 is the vertical Mill
    PositionCircle player1PositionCircle1 = from(rootNode).lookup("#position1").query();
    clickOn(player1PositionCircle1);
    PositionCircle player2PositionCircle1 = from(rootNode).lookup("#position4").query();
    clickOn(player2PositionCircle1);
    PositionCircle player1PositionCircle2 = from(rootNode).lookup("#position2").query();
    clickOn(player1PositionCircle2);
    PositionCircle player2PositionCircle2 = from(rootNode).lookup("#position6").query();
    clickOn(player2PositionCircle2);
    PositionCircle player1PositionCircle3 = from(rootNode).lookup("#position3").query();
    clickOn(player1PositionCircle3);

    assertEquals(GameConstants.REMOVE, gameManagerGUI.getBoard().getOp());
  }

  @Test
  public void removeOpponentPlayerCoin() {
    // 1,2,3 is the vertical Mill
    PositionCircle player1PositionCircle1 = from(rootNode).lookup("#position1").query();
    clickOn(player1PositionCircle1);
    PositionCircle player2PositionCircle1 = from(rootNode).lookup("#position4").query();
    clickOn(player2PositionCircle1);
    PositionCircle player1PositionCircle2 = from(rootNode).lookup("#position2").query();
    clickOn(player1PositionCircle2);
    PositionCircle player2PositionCircle2 = from(rootNode).lookup("#position6").query();
    clickOn(player2PositionCircle2);
    PositionCircle player1PositionCircle3 = from(rootNode).lookup("#position3").query();
    clickOn(player1PositionCircle3);
    // remove
    clickOn(player2PositionCircle2);
    assertEquals(player2PositionCircle2.getFill(), GameConstants.BACKGROUNDCOLOR);
  }

  @Test
  public void removeSamePlayerCoin() {
    // 1,2,3 is the vertical Mill
    PositionCircle player1PositionCircle1 = from(rootNode).lookup("#position1").query();
    clickOn(player1PositionCircle1);
    PositionCircle player2PositionCircle1 = from(rootNode).lookup("#position4").query();
    clickOn(player2PositionCircle1);
    PositionCircle player1PositionCircle2 = from(rootNode).lookup("#position2").query();
    clickOn(player1PositionCircle2);
    PositionCircle player2PositionCircle2 = from(rootNode).lookup("#position6").query();
    clickOn(player2PositionCircle2);
    PositionCircle player1PositionCircle3 = from(rootNode).lookup("#position3").query();
    clickOn(player1PositionCircle3);
    // remove
    clickOn(player1PositionCircle2);
    assertNotEquals(player1PositionCircle2.getFill(), GameConstants.BACKGROUNDCOLOR);
    assertTrue(player1PositionCircle2.isDisabled());
  }

  // blankCirclesDisabledWhileRemoving
  @Test
  public void blankCirclesDisabledWhileRemoving() {
    // 1,2,3 is the vertical Mill
    PositionCircle player1PositionCircle1 = from(rootNode).lookup("#position1").query();
    clickOn(player1PositionCircle1);
    PositionCircle player2PositionCircle1 = from(rootNode).lookup("#position4").query();
    clickOn(player2PositionCircle1);
    PositionCircle player1PositionCircle2 = from(rootNode).lookup("#position2").query();
    clickOn(player1PositionCircle2);
    PositionCircle player2PositionCircle2 = from(rootNode).lookup("#position6").query();
    clickOn(player2PositionCircle2);
    PositionCircle player1PositionCircle3 = from(rootNode).lookup("#position3").query();
    clickOn(player1PositionCircle3);
    // remove
    for(Position p : gameManagerGUI.getBoard().getAllPositionList().stream()
            .filter(pos -> !pos.isFilled()).collect(Collectors.toList())){
    	assertEquals(true, p.getPositionCircle().isDisabled());
    }
  }

  
}
