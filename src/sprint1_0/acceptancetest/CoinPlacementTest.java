package sprint1_0.acceptancetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.PositionCircle;
import sprint1_0.product.ui.GameManagerGUI;

/** @author ravali */
public class CoinPlacementTest extends ApplicationTest {
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
  public void testifFilledCircleisDisabled() {
    PositionCircle positionCircle = from(rootNode).lookup("#position1").query();
    clickOn(positionCircle);
    assertEquals(true, positionCircle.isDisabled());
  }

  @Test
  public void testSuccessfulCoinPlacementOfPlayerOnEmptyCircle() {
    PositionCircle positionCircle = from(rootNode).lookup("#position1").query();
    Color displayColor =
        (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    clickOn(positionCircle);
    assertEquals(displayColor, positionCircle.getFill());
  }

  @Test
  public void testColorOfCircleOnFilledCircleAfterPlayerTurn() {
    PositionCircle positionCircle = from(rootNode).lookup("#position1").query();
    Color displayColor =
        (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    clickOn(positionCircle);
    if (displayColor.equals(GameConstants.PLAYER1COLOR))
      assertEquals(GameConstants.PLAYER1COLOR, positionCircle.getFill());
    else if (displayColor.equals(GameConstants.PLAYER2COLOR))
      assertEquals(GameConstants.PLAYER2COLOR, positionCircle.getFill());
  }

  @Test
  public void testDisplayColorAndTextChangeAfterPlayerMove() {
    PositionCircle positionCircle1 = from(rootNode).lookup("#position1").query();
    clickOn(positionCircle1);
    Color displayColorBefore =
        (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    String displayTextBefore = ((Text) from(rootNode).lookup("#displayText").query()).getText();
    PositionCircle positionCircle2 = from(rootNode).lookup("#position2").query();
    clickOn(positionCircle2);
    Color displayColorAfter =
        (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    String displayTextAfter = ((Text) from(rootNode).lookup("#displayText").query()).getText();
    assertNotEquals(displayColorBefore, displayColorAfter);
    assertNotEquals(displayTextBefore, displayTextAfter);
  }

  // player1 coin decrease test
  @Test
  public void testPlayerCoinsDecreaseAfterPlayerMove() {
    Color displayColor =
        (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    PositionCircle positionCircle1 = from(rootNode).lookup("#position1").query();
    int player1sizeBefore = gameManagerGUI.getBoard().getPlayer1().getCoins().size();
    int player2sizeBefore = gameManagerGUI.getBoard().getPlayer2().getCoins().size();
    if (displayColor.equals(GameConstants.PLAYER1COLOR)) {
      clickOn(positionCircle1);
      int player1sizeAfter = gameManagerGUI.getBoard().getPlayer1().getCoins().size();
      assertEquals(player1sizeBefore, player1sizeAfter + 1);
    } else if (displayColor.equals(GameConstants.PLAYER2COLOR)) {
      clickOn(positionCircle1);
      int player2sizeAfter = gameManagerGUI.getBoard().getPlayer2().getCoins().size();
      assertEquals(player2sizeBefore, player2sizeAfter + 1);
    }
  }
  
  @Test
  public void testIfBlankCirclesAreClickable() {
	  int i=0;
	  while(!gameManagerGUI.getBoard().getPlayer1().getCoins().isEmpty()
              && !gameManagerGUI.getBoard().getPlayer2().getCoins().isEmpty()) {
	      String pos = "#position" + String.valueOf(i + 1);
	      PositionCircle c = from(rootNode).lookup(pos).query();
	      clickOn(c);
	      i++;
	    }
	    for (int j = 0; j < gameManagerGUI.getBoard().getBlankPositionList().size(); j++) {
	        String pos = "#position" + String.valueOf(j + 1);
	        PositionCircle c = from(rootNode).lookup(pos).query();
	        assertEquals(true, c.isDisabled());
	      }
	  }
}
