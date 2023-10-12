package sprint1_0.acceptancetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import junit.framework.Assert;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.PositionCircle;
import sprint1_0.product.ui.GameManagerGUI;

public class BoardVisualisationTest extends ApplicationTest {
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
	
//hasNewGmaeButton
  @Test
  public void hasNewGameButton() {
    Button button = from(rootNode).lookup("#newGameButton").query();
    assertEquals("NEW GAME", button.getText());
  }
//hasDecideButton
  @Test
  public void hasDecideButton() {
    Button button = from(rootNode).lookup("#decideButton").query();
    assertEquals("DECIDE", button.getText());
  }
  // hasresetbutton
  @Test
  public void hasresetButton() {
    Button button = from(rootNode).lookup("#resetGameButton").query();
    assertEquals("RESET GAME", button.getText());
  }
//is decideButtonDisabled
  @Test
  public void isDecidedButtonDisabled() {

    Button button = from(rootNode).lookup("#decideButton").query();
    assertEquals(true, button.isDisabled());
  }

  // isresetButtondisabled
  @Test
  public void isresetButtonDisabled() {

    Button button = from(rootNode).lookup("#resetGameButton").query();
    assertEquals(true, button.isDisabled());
  }


  // isnewGamebuttonenabled
  @Test
  public void isNewGameButtonDisabled() {

    Button button = from(rootNode).lookup("#newGameButton").query();
    assertEquals(false, button.isDisabled());
  }

//onClickofNewGameButtonIsDecideButtonDisbled()

  @Test
  public void onClickofNewGameButtonIsDecidedButtonEnabled() {
    Button newGameButton = from(rootNode).lookup("#newGameButton").query();
    Button decideButton = from(rootNode).lookup("#decideButton").query();
    clickOn(newGameButton);
    assertEquals(false, decideButton.isDisabled());
  }

  // onClickofNewGameButtonIsNewGameButtonDisbled()
  @Test
  public void onClickofNewGameButtonIsNewGameButtonDisabled() {
    Button newGameButton = from(rootNode).lookup("#newGameButton").query();
    clickOn(newGameButton);
    assertEquals(true, newGameButton.isDisabled());
  }


  // onClickofNewGameButtonIsResetGameButtonEnabled()
  @Test
  public void onClickofNewGameButtonIsResetGameButtonDisabled() {
    Button newGameButton = from(rootNode).lookup("#newGameButton").query();
    Button resetGameButton = from(rootNode).lookup("#resetGameButton").query();
    clickOn(newGameButton);
    assertEquals(false, resetGameButton.isDisabled());
  }
  

  // checkDisplayText
  @Test
  public void checkDisplayText()
  {
	   Text displayText = from(rootNode).lookup("#displayText").query();
	   @SuppressWarnings("unused")
	String text = displayText.getText();
	   assertEquals("Who's Turn ?",displayText);
  }

  // checkDisplayCircle

  @Test
  public void checkifAllPositionsAreAvailableAndNotClickable() {
    for (int i = 0; i < GameConstants.NUM_POSITIONS_OF_BOARD; i++) {
      String pos = "#position" + String.valueOf(i+1);
      PositionCircle c = from(rootNode).lookup(pos).query();
      assertEquals(true, c.isDisabled());
    }
  }

  // checkifPlayer1andPlayer2 text is present

  // checkifPlayer1 has 9 same color coins

  // checkifPlayer2 has 9 same color coins

  // checkifColors are unique for both player1 and player2

}
