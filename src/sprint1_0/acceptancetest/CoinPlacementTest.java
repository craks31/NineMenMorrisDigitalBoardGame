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
  		PositionCircle positionCircle = from(rootNode).lookup("#position1").query();
  		positionCircle.setDisable(false);
  		positionCircle.setFill(GameConstants.BACKGROUNDCOLOR);
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
  public void testColorOfPlayer1CoinOnFilledCircleAfterPlayer1Turn() {
    PositionCircle positionCircle = from(rootNode).lookup("#position1").query();
    Color displayColor = (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    clickOn(positionCircle);
    if (displayColor.equals(GameConstants.PLAYER1COLOR))
      assertEquals(GameConstants.PLAYER1COLOR, positionCircle.getFill());
  }
  
  @Test
  public void testColorOfPlayer2CoinOnFilledCircleAfterPlayer2Turn() {
    PositionCircle positionCircle = from(rootNode).lookup("#position1").query();
    Color displayColor = (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    clickOn(positionCircle);
    if (displayColor.equals(GameConstants.PLAYER2COLOR))
      assertEquals(GameConstants.PLAYER2COLOR, positionCircle.getFill());
  }
  
  @Test
  public void testColorOfPlayer1CoinOnFilledCircleOnPlayer2Turn() {
    PositionCircle positionCircle = from(rootNode).lookup("#position1").query();
    Color displayColor = (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    clickOn(positionCircle);
    if (displayColor.equals(GameConstants.PLAYER2COLOR))
      assertNotEquals(GameConstants.PLAYER1COLOR, positionCircle.getFill());
  }
  
  @Test
  public void testColorOfPlayer2CoinOnFilledCircleOnPlayer1Turn() {
    PositionCircle positionCircle = from(rootNode).lookup("#position1").query();
    Color displayColor = (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    clickOn(positionCircle);
    if (displayColor.equals(GameConstants.PLAYER1COLOR))
      assertNotEquals(GameConstants.PLAYER2COLOR, positionCircle.getFill());
  }
  
  

  // displayAndColortestAfterPlacementForPlayer1
  
  @Test
  public void testDisplayColorAndTextAfterPlayer1Move() {
    PositionCircle positionCircle = from(rootNode).lookup("#position1").query();
    Color displayColorBefore = (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    Text displayTextBefore = from(rootNode).lookup("#displayText").query();
    clickOn(positionCircle);
    if (displayColorBefore.equals(GameConstants.PLAYER1COLOR))
      assertNotEquals(GameConstants.PLAYER2COLOR, positionCircle.getFill());
  }
  @Test
  public void testDisplayColorAndTextAfterPlayer2Move() {
    PositionCircle positionCircle = from(rootNode).lookup("#position1").query();
    Color displayColor = (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    Text displayText = from(rootNode).lookup("#displayText").query();
    clickOn(positionCircle);
    if (displayColor.equals(GameConstants.PLAYER1COLOR))
      assertNotEquals(GameConstants.PLAYER2COLOR, positionCircle.getFill());
  }
  // displayAndColortestAfterPlacementForPlayer2
  
  // player1 coin decrease test
  
  // player2 coin decrease test
  
  // fill not more than 24 positions
  
  
  
  
}
