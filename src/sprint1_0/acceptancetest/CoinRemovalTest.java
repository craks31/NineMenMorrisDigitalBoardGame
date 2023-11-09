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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
    for (Position p :
        gameManagerGUI.getBoard().getAllPositionList().stream()
            .filter(pos -> !pos.isFilled())
            .collect(Collectors.toList())) {
      assertEquals(true, p.getPositionCircle().isDisabled());
    }
  }

  @Test
  public void movingBacktoFillPositionAfterRemoval() {
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
    assertEquals(GameConstants.FILL, gameManagerGUI.getBoard().getOp());
  }

  @Test
  public void movingBcktoMovePositionAfterRemoval() {
    // 1,2,3 is the vertical Mill
    int i = 0;
    gameManagerGUI.getBoard().setMillCheckByPassed(true);
    while ((!gameManagerGUI.getBoard().getPlayer2().getCoins().isEmpty())
        || (!gameManagerGUI.getBoard().getPlayer1().getCoins().isEmpty())) {
      String pos = "#position" + String.valueOf(i + 1);
      PositionCircle c = from(rootNode).lookup(pos).query();
      clickOn(c);
      i++;
    }
    gameManagerGUI.getBoard().setMillCheckByPassed(false);
    Color displayColor =
        (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    while (!gameManagerGUI.getBoard().getOp().equals(GameConstants.REMOVE)) {
      if (displayColor.equals(GameConstants.PLAYER1COLOR)) {
        Position position =
            gameManagerGUI.getBoard().getPlayer1().getFilledPositions().stream().findFirst().get();
        moveFunction(position, displayColor);
      } else if (displayColor.equals(GameConstants.PLAYER2COLOR)) {
        Position position =
            gameManagerGUI.getBoard().getPlayer2().getFilledPositions().stream().findFirst().get();
        moveFunction(position, displayColor);
      }
    }
    System.out.println(gameManagerGUI.getBoard().getOp());
    // clickOn(player2PositionCircle2);
    assertEquals(GameConstants.REMOVE, gameManagerGUI.getBoard().getOp());
  }

  private void moveFunction(Position position, Color displayColor) {
    String pos = "#" + String.valueOf(position.getPositionCircle().getId());
    PositionCircle positionCircle = from(rootNode).lookup(pos).query();
    gameManagerGUI.getBoard().setOp(GameConstants.MOVE);
    clickOn(positionCircle);

    if (position.getUp() != null && !position.getUp().isFilled()) {
      String posup = "#" + String.valueOf(position.getUp().getPositionCircle().getId());
      PositionCircle positionCircleup = from(rootNode).lookup(posup).query();
      clickOn(positionCircleup);
      assertEquals(displayColor, positionCircleup.getFill());
      assertEquals(GameConstants.BACKGROUNDCOLOR, positionCircle.getFill());
    } else if (position.getLeft() != null && !position.getLeft().isFilled()) {
      String posLeft = "#" + String.valueOf(position.getUp().getPositionCircle().getId());
      PositionCircle positionCircleLeft = from(rootNode).lookup(posLeft).query();
      clickOn(positionCircleLeft);
      assertEquals(displayColor, positionCircleLeft.getFill());
      assertEquals(GameConstants.BACKGROUNDCOLOR, positionCircle.getFill());
    } else if (position.getRight() != null && !position.getRight().isFilled()) {
      String posRight = "#" + String.valueOf(position.getUp().getPositionCircle().getId());
      PositionCircle positionCircleRight = from(rootNode).lookup(posRight).query();
      clickOn(positionCircleRight);
      assertEquals(displayColor, positionCircleRight.getFill());
      assertEquals(GameConstants.BACKGROUNDCOLOR, positionCircle.getFill());
    } else if (position.getDown() != null && !position.getDown().isFilled()) {
      String posDown = "#" + String.valueOf(position.getUp().getPositionCircle().getId());
      PositionCircle positionCircleDown = from(rootNode).lookup(posDown).query();
      clickOn(positionCircleDown);
      assertEquals(displayColor, positionCircleDown.getFill());
      assertEquals(GameConstants.BACKGROUNDCOLOR, positionCircle.getFill());
    }
  }
}
