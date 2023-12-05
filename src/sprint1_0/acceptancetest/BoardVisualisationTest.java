package sprint1_0.acceptancetest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.PositionCircle;
import sprint1_0.product.ui.GameManagerGUI;

/** @author pujitha & Rakesh */
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

  // hasNewGmaeButton
  @Test
  public void hasNewGameButton() {
    Button button = from(rootNode).lookup("#newGameButton").query();
    assertEquals("NEW GAME", button.getText());
  }
  // hasDecideButton
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
  // is decideButtonDisabled
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

  // onClickofNewGameButtonIsDecideButtonDisbled()

  @Test
  public void onClickofNewGameButtonIsDecidedButtonEnabled() {
    Button newGameButton = from(rootNode).lookup("#newGameButton").query();
    Button decideButton = from(rootNode).lookup("#decideButton").query();
    clickOn(newGameButton);
    ChoiceDialog<String> choiceDialog = gameManagerGUI.getBoard().getChoiceDialog();
    TextInputDialog name1Dialog = gameManagerGUI.getBoard().getName1Dialog();
    TextInputDialog name2Dialog = gameManagerGUI.getBoard().getName2Dialog();
    Platform.runLater(
        () -> {
          choiceDialog.setSelectedItem("Human");
          ((Button) choiceDialog.getDialogPane().lookupButton(ButtonType.OK)).fire();
        });
    Platform.runLater(
            () -> {
              ((Button) name1Dialog.getDialogPane().lookupButton(ButtonType.OK)).fire();
              ((Button) name2Dialog.getDialogPane().lookupButton(ButtonType.OK)).fire();
            });
   
    assertEquals(false, decideButton.isDisabled());
  }

  // onClickofNewGameButtonIsNewGameButtonDisbled()
  @Test
  public void onClickofNewGameButtonIsNewGameButtonDisabled() {
    Button newGameButton = from(rootNode).lookup("#newGameButton").query();
    clickOn(newGameButton);
    ChoiceDialog<String> choiceDialog = gameManagerGUI.getBoard().getChoiceDialog();
    TextInputDialog name1Dialog = gameManagerGUI.getBoard().getName1Dialog();
    TextInputDialog name2Dialog = gameManagerGUI.getBoard().getName2Dialog();
    Platform.runLater(
        () -> {
          choiceDialog.setSelectedItem("Human");
          ((Button) choiceDialog.getDialogPane().lookupButton(ButtonType.OK)).fire();
        });
    Platform.runLater(
            () -> {
              ((Button) name1Dialog.getDialogPane().lookupButton(ButtonType.OK)).fire();
              ((Button) name2Dialog.getDialogPane().lookupButton(ButtonType.OK)).fire();
            });
    assertEquals(true, newGameButton.isDisabled());
  }

  // onClickofNewGameButtonIsResetGameButtonEnabled()
  @Test
  public void onClickofNewGameButtonIsResetGameButtonDisabled() {
    Button newGameButton = from(rootNode).lookup("#newGameButton").query();
    Button resetGameButton = from(rootNode).lookup("#resetGameButton").query();
    clickOn(newGameButton);
    ChoiceDialog<String> choiceDialog = gameManagerGUI.getBoard().getChoiceDialog();
    TextInputDialog name1Dialog = gameManagerGUI.getBoard().getName1Dialog();
    TextInputDialog name2Dialog = gameManagerGUI.getBoard().getName2Dialog();
    Platform.runLater(
        () -> {
          choiceDialog.setSelectedItem("Human");
          ((Button) choiceDialog.getDialogPane().lookupButton(ButtonType.OK)).fire();
        });
    Platform.runLater(
            () -> {
              ((Button) name1Dialog.getDialogPane().lookupButton(ButtonType.OK)).fire();
              ((Button) name2Dialog.getDialogPane().lookupButton(ButtonType.OK)).fire();
            });
    assertEquals(false, resetGameButton.isDisabled());
  }

  // checkDisplayText
  @Test
  public void checkDisplayText() {
    String displayText = ((Text) (from(rootNode).lookup("#displayText").query())).getText();
    assertEquals("Who's Turn ?", displayText);
  }

  @Test
  public void checkifAllPositionsAreAvailableAndNotClickable() {
    for (int i = 0; i < GameConstants.NUM_POSITIONS_OF_BOARD; i++) {
      String pos = "#position" + String.valueOf(i);
      PositionCircle c = from(rootNode).lookup(pos).query();
      assertEquals(true, c.isDisabled());
    }
  }

  // checkifPlayer1andPlayer2 text is present
  @Test
  public void checkifPlayer1andPlayer2Text() {
    String player1Text = ((Text) (from(rootNode).lookup("#player1Text").query())).getText();
    assertEquals("PLAYER 1", player1Text);
    String player2Text = ((Text) (from(rootNode).lookup("#player2Text").query())).getText();
    assertEquals("PLAYER 2", player2Text);
  }

  // checkifPlayer1AndPlayer2 has 9 same color coins
  @Test
  public void checkifPlayer1andPlayer2Has9CoinsEach() {
    List<Node> player1Coins =
        ((Group) (from(rootNode).lookup("#player1CoinsGroup").query())).getChildren();
    assertEquals(9, player1Coins.size());
    List<Node> player2Coins =
        ((Group) (from(rootNode).lookup("#player2CoinsGroup").query())).getChildren();
    assertEquals(9, player2Coins.size());
  }
}
