package sprint1_0.acceptancetest;

// import static /.;
import static org.junit.Assert.assertEquals;

import java.util.List;
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

public class CoinMovementTest extends ApplicationTest {
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

  // 4.3
  @Test
  public void filledCirclesClickableTest() {
    int i = 0;
    Color displayColor =
        (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    while ((!gameManagerGUI.getBoard().getPlayer2().getCoins().isEmpty())
        || (!gameManagerGUI.getBoard().getPlayer1().getCoins().isEmpty())) {
      String pos = "#position" + String.valueOf(i + 1);
      PositionCircle c = from(rootNode).lookup(pos).query();
      clickOn(c);
      i++;
    }
    if (displayColor.equals(GameConstants.PLAYER1COLOR)) {
      for (Position p : gameManagerGUI.getBoard().getPlayer1().getFilledPositions()) {
        PositionCircle positionCircle =
            from(rootNode).lookup("#" + p.getPositionCircle().getId()).query();
        assertEquals(false, positionCircle.isDisabled());
      }
      for (Position p : gameManagerGUI.getBoard().getPlayer2().getFilledPositions()) {
        PositionCircle positionCircle =
            from(rootNode).lookup("#" + p.getPositionCircle().getId()).query();
        assertEquals(true, positionCircle.isDisabled());
      }
    } else if (displayColor.equals(GameConstants.PLAYER2COLOR)) {
      for (Position p : gameManagerGUI.getBoard().getPlayer2().getFilledPositions()) {
        PositionCircle positionCircle =
            from(rootNode).lookup("#" + p.getPositionCircle().getId()).query();
        assertEquals(false, positionCircle.isDisabled());
      }
      for (Position p : gameManagerGUI.getBoard().getPlayer1().getFilledPositions()) {
        PositionCircle positionCircle =
            from(rootNode).lookup("#" + p.getPositionCircle().getId()).query();
        assertEquals(true, positionCircle.isDisabled());
      }
    }
  }

  // 4.2
  @Test
  public void testifNonNeighborBlankCirclesAreDisabledAfterDecidingToMoveAnyCoin() {
    int i = 0;
    Color displayColor =
        (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    gameManagerGUI.getBoard().setMillCheckByPassed(true);
    while ((!gameManagerGUI.getBoard().getPlayer2().getCoins().isEmpty())
        || (!gameManagerGUI.getBoard().getPlayer1().getCoins().isEmpty())) {
      String pos = "#position" + String.valueOf(i + 1);
      PositionCircle c = from(rootNode).lookup(pos).query();
      clickOn(c);
      i++;
    }
    List<Position> blankPositionList =
        gameManagerGUI.getBoard().getAllPositionList().stream()
            .filter(p -> !p.isFilled())
            .collect(Collectors.toList());

    if (displayColor.equals(GameConstants.PLAYER1COLOR)) {
      Position position =
          gameManagerGUI.getBoard().getPlayer1().getFilledPositions().stream().findFirst().get();

      String pos = "#" + String.valueOf(position.getPositionCircle().getId());
      PositionCircle positionCircle = from(rootNode).lookup(pos).query();
      gameManagerGUI.getBoard().setOp(GameConstants.MOVE);
      clickOn(positionCircle);

      if (position.getUp() != null && !position.getUp().isFilled()) {
        blankPositionList.remove(position.getUp());
        System.out.println("removed position.getUp()");
      }
      if (position.getLeft() != null && !position.getLeft().isFilled()) {
        blankPositionList.remove(position.getLeft());
        System.out.println("removed position.getL()");
      }
      if (position.getRight() != null && !position.getRight().isFilled()) {
        blankPositionList.remove(position.getRight());
        System.out.println("removed position.getR()");
      }
      if (position.getDown() != null && !position.getDown().isFilled()) {
        blankPositionList.remove(position.getDown());
        System.out.println("removed position.getd()");
      }
    } else {
      Position position =
          gameManagerGUI.getBoard().getPlayer2().getFilledPositions().stream().findFirst().get();
      String pos = "#" + String.valueOf(position.getPositionCircle().getId());
      PositionCircle positionCircle = from(rootNode).lookup(pos).query();
      gameManagerGUI.getBoard().setOp(GameConstants.MOVE);
      clickOn(positionCircle);
      if (position.getUp() != null && !position.getUp().isFilled()) {
        blankPositionList.remove(position.getUp());
        System.out.println("removed position.getUp()");
      }
      if (position.getLeft() != null && !position.getLeft().isFilled()) {
        blankPositionList.remove(position.getLeft());
        System.out.println("removed position.getL()");
      }
      if (position.getRight() != null && !position.getRight().isFilled()) {
        blankPositionList.remove(position.getRight());
        System.out.println("removed position.getR()");
      }
      if (position.getDown() != null && !position.getDown().isFilled()) {
        blankPositionList.remove(position.getDown());
        System.out.println("removed position.getD()");
      }
    }
    for (Position p : blankPositionList) {
      String pos = "#" + String.valueOf(p.getPositionCircle().getId());
      PositionCircle positionCircle = from(rootNode).lookup(pos).query();
      assertEquals(true, positionCircle.isDisabled());
    }
  }

  // 4.6
  @Test
  public void testifBlankCirclesAreDisabledWithoutMovingAnyCoin() {
    int i = 0;
    gameManagerGUI.getBoard().setMillCheckByPassed(true);
    while ((!gameManagerGUI.getBoard().getPlayer2().getCoins().isEmpty())
        || (!gameManagerGUI.getBoard().getPlayer1().getCoins().isEmpty())) {
      String pos = "#position" + String.valueOf(i + 1);
      PositionCircle c = from(rootNode).lookup(pos).query();
      clickOn(c);
      i++;
    }
    for (Position p :
        gameManagerGUI.getBoard().getAllPositionList().stream()
            .filter(p -> !p.isFilled())
            .collect(Collectors.toList())) {
      String pos = "#" + String.valueOf(p.getPositionCircle().getId());
      PositionCircle positionCircle = from(rootNode).lookup(pos).query();
      assertEquals(true, positionCircle.isDisabled());
    }
  }
  // 4.1
  @Test
  public void testNeighborsOfClickedCircle() {
    int i = 0;
    Color displayColor =
        (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    gameManagerGUI.getBoard().setMillCheckByPassed(true);
    while ((!gameManagerGUI.getBoard().getPlayer2().getCoins().isEmpty())
        || (!gameManagerGUI.getBoard().getPlayer1().getCoins().isEmpty())) {
      String pos = "#position" + String.valueOf(i + 1);
      PositionCircle c = from(rootNode).lookup(pos).query();
      clickOn(c);
      i++;
    }
    if (displayColor.equals(GameConstants.PLAYER1COLOR)) {
      Position position =
          gameManagerGUI.getBoard().getPlayer1().getFilledPositions().stream().findFirst().get();

      String pos = "#" + String.valueOf(position.getPositionCircle().getId());
      PositionCircle positionCircle = from(rootNode).lookup(pos).query();
      gameManagerGUI.getBoard().setOp(GameConstants.MOVE);
      clickOn(positionCircle);

      if (position.getUp() != null && !position.getUp().isFilled()) {
        assertEquals(false, positionCircle.isDisabled());
      } else if (position.getUp() != null && position.getUp().isFilled()) {
        assertEquals(true, positionCircle.isDisabled());
      }
      if (position.getLeft() != null && !position.getLeft().isFilled()) {
        assertEquals(false, positionCircle.isDisabled());
      } else if (position.getLeft() != null && position.getLeft().isFilled()) {
        assertEquals(true, positionCircle.isDisabled());
      }
      if (position.getRight() != null && !position.getRight().isFilled()) {
        assertEquals(false, positionCircle.isDisabled());
      } else if (position.getRight() != null && position.getRight().isFilled()) {
        assertEquals(true, positionCircle.isDisabled());
      }
      if (position.getDown() != null && !position.getDown().isFilled()) {
        assertEquals(false, positionCircle.isDisabled());
      } else if (position.getDown() != null && position.getDown().isFilled()) {
        assertEquals(true, positionCircle.isDisabled());
      }
    } else {
      Position position =
          gameManagerGUI.getBoard().getPlayer2().getFilledPositions().stream().findFirst().get();
      String pos = "#" + String.valueOf(position.getPositionCircle().getId());
      PositionCircle positionCircle = from(rootNode).lookup(pos).query();
      gameManagerGUI.getBoard().setOp(GameConstants.MOVE);
      clickOn(positionCircle);
      if (position.getUp() != null && !position.getUp().isFilled()) {
        assertEquals(false, positionCircle.isDisabled());
      } else if (position.getUp() != null && position.getUp().isFilled()) {
        assertEquals(true, positionCircle.isDisabled());
      }
      if (position.getLeft() != null && !position.getLeft().isFilled()) {
        assertEquals(false, positionCircle.isDisabled());
      } else if (position.getLeft() != null && position.getLeft().isFilled()) {
        assertEquals(true, positionCircle.isDisabled());
      }
      if (position.getRight() != null && !position.getRight().isFilled()) {
        assertEquals(false, positionCircle.isDisabled());
      } else if (position.getRight() != null && position.getRight().isFilled()) {
        assertEquals(true, positionCircle.isDisabled());
      }
      if (position.getDown() != null && !position.getDown().isFilled()) {
        assertEquals(false, positionCircle.isDisabled());
      } else if (position.getDown() != null && position.getDown().isFilled()) {
        assertEquals(true, positionCircle.isDisabled());
      }
    }
  }

  // 4.4
  @Test
  public void testSuccessfulMove() {
    int i = 0;
    Color displayColor =
        (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    gameManagerGUI.getBoard().setMillCheckByPassed(true);
    while ((!gameManagerGUI.getBoard().getPlayer2().getCoins().isEmpty())
        || (!gameManagerGUI.getBoard().getPlayer1().getCoins().isEmpty())) {
      String pos = "#position" + String.valueOf(i + 1);
      PositionCircle c = from(rootNode).lookup(pos).query();
      clickOn(c);
      i++;
    }
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

  // 4.5
  @Test
  public void testConcurrentSuccessfulMoves() {
    int i = 0;
    Color displayColor =
        (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
    gameManagerGUI.getBoard().setMillCheckByPassed(true);
    while ((!gameManagerGUI.getBoard().getPlayer2().getCoins().isEmpty())
        || (!gameManagerGUI.getBoard().getPlayer1().getCoins().isEmpty())) {
      String pos = "#position" + String.valueOf(i + 1);
      PositionCircle c = from(rootNode).lookup(pos).query();
      clickOn(c);
      i++;
    }
    if (displayColor.equals(GameConstants.PLAYER1COLOR)) {
      Position position =
          gameManagerGUI.getBoard().getPlayer1().getFilledPositions().stream().findFirst().get();
      moveFunction(position, displayColor);
      Color newDisplayColor =
          (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
      Position newPosition =
          gameManagerGUI.getBoard().getPlayer2().getFilledPositions().stream().findFirst().get();
      moveFunction(newPosition, newDisplayColor);
    } else if (displayColor.equals(GameConstants.PLAYER2COLOR)) {
      Position position =
          gameManagerGUI.getBoard().getPlayer2().getFilledPositions().stream().findFirst().get();
      moveFunction(position, displayColor);
      Color newDisplayColor =
          (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
      Position newPosition =
          gameManagerGUI.getBoard().getPlayer1().getFilledPositions().stream().findFirst().get();
      moveFunction(newPosition, newDisplayColor);
    }
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
