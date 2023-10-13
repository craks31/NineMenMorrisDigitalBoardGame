package sprint1_0.acceptancetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.controller.GameController;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.PositionCircle;
import sprint1_0.product.ui.GameManagerGUI;

/**
 * @author rakesh
 *
 */
public class CoinPlacementTest extends ApplicationTest {

  private Board board;
  private GameController gameController;
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
    Color displayColor = (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    clickOn(positionCircle);
    assertEquals(displayColor, positionCircle.getFill());
  }

  @Test
  public void testColorOfCircleOnFilledCircleAfterPlayerTurn() {
    PositionCircle positionCircle = from(rootNode).lookup("#position1").query();
    Color displayColor = (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
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
    String displayTextBefore = ((Text)from(rootNode).lookup("#displayText").query()).getText();
    PositionCircle positionCircle2 = from(rootNode).lookup("#position2").query();
    clickOn(positionCircle2);
    Color displayColorAfter =
        (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    String displayTextAfter = ((Text)from(rootNode).lookup("#displayText").query()).getText();
    assertNotEquals(displayColorBefore, displayColorAfter);
    assertNotEquals(displayTextBefore, displayTextAfter);
  }
  
  // player1 coin decrease test
  
  // player2 coin decrease test
  
  // fill not more than 24 positions
  
  
  
  
}
