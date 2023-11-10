package sprint1_0.acceptancetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.PositionCircle;
import sprint1_0.product.service.CoinFlyService;
import sprint1_0.product.ui.GameManagerGUI;

public class CoinFlyTest extends ApplicationTest {
  private GameManagerGUI gameManagerGUI = new GameManagerGUI();
  CoinFlyService coinFlyService = new CoinFlyService();
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
  public void successfulFlyToABlankPosition() {
    // Setting up the board with only 3 players of one player and 3 players of one player
    PositionCircle srcPositionPlayer1 = from(rootNode).lookup("#position1").query();
    clickOn(srcPositionPlayer1);
    PositionCircle player2PositionCircle1 = from(rootNode).lookup("#position2").query();
    clickOn(player2PositionCircle1);
    PositionCircle player1PositionCircle2 = from(rootNode).lookup("#position3").query();
    clickOn(player1PositionCircle2);
    PositionCircle player2PositionCircle2 = from(rootNode).lookup("#position14").query();
    clickOn(player2PositionCircle2);
    PositionCircle player1PositionCircle3 = from(rootNode).lookup("#position19").query();
    clickOn(player1PositionCircle3);
    PositionCircle player2PositionCircle3 = from(rootNode).lookup("#position16").query();
    clickOn(player2PositionCircle3);

    Color srcColor = (Color) srcPositionPlayer1.getFill();
    coinFlyService.prepareForCoinFlyMovement(gameManagerGUI.getBoard(), srcColor);
    clickOn(srcPositionPlayer1);
    PositionCircle destPosition = from(rootNode).lookup("#position7").query();
    assertFalse(destPosition.isDisabled()); // MAKE SURE IT IS clickable
    assertEquals((Color) destPosition.getFill(), GameConstants.BACKGROUNDCOLOR);
    clickOn(destPosition);
    assertEquals((Color) destPosition.getFill(), srcColor);
    assertEquals((Color) srcPositionPlayer1.getFill(), GameConstants.BACKGROUNDCOLOR);
  }

  @Test
  public void unSuccessfulFlyToAFilledPosition() {
	// Setting up the board with only 3 players of one player and 3 players of one player
	    PositionCircle srcPositionPlayer1 = from(rootNode).lookup("#position1").query();
	    clickOn(srcPositionPlayer1);
	    PositionCircle destPositionPlayer2 = from(rootNode).lookup("#position2").query();
	    clickOn(destPositionPlayer2);
	    PositionCircle destPositionPlayer1 = from(rootNode).lookup("#position3").query();
	    clickOn(destPositionPlayer1);
	    PositionCircle player2PositionCircle2 = from(rootNode).lookup("#position14").query();
	    clickOn(player2PositionCircle2);
	    PositionCircle player1PositionCircle3 = from(rootNode).lookup("#position19").query();
	    clickOn(player1PositionCircle3);
	    PositionCircle player2PositionCircle3 = from(rootNode).lookup("#position16").query();
	    clickOn(player2PositionCircle3);

	    Color srcColor = (Color) srcPositionPlayer1.getFill();
	    coinFlyService.prepareForCoinFlyMovement(gameManagerGUI.getBoard(), srcColor);
	    clickOn(srcPositionPlayer1);
	    assertTrue(destPositionPlayer2.isDisabled()); // MAKE SURE IT IS NOT CLICKABLE
	    assertTrue(destPositionPlayer1.isDisabled()); // MAKE SURE IT IS NOT CLICKABLE

  }
}
