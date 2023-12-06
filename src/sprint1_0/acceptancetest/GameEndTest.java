package sprint1_0.acceptancetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
import sprint1_0.product.model.Position;
import sprint1_0.product.model.PositionCircle;
import sprint1_0.product.service.CoinFlyService;
import sprint1_0.product.service.GameEndService;
import sprint1_0.product.ui.NineMenMorrisGameManagerGUI;

public class GameEndTest extends ApplicationTest {
	private NineMenMorrisGameManagerGUI gameManagerGUI = new NineMenMorrisGameManagerGUI();
	CoinFlyService coinFlyService = new CoinFlyService();
	GameEndService gameEndService = new GameEndService();
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
	public void decideWinningCondition() {
		// Setting up the board with only 3 players of one player and 2 players of one
		// player
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
		Color displayColor = (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
		if (displayColor.equals(GameConstants.PLAYER1COLOR)) {
			List<Position> player1filledPositions = new ArrayList<>(3);
			List<Position> player2filledPositions = new ArrayList<>(2);
			Position p1 = new Position();
			player2filledPositions.add(p1);
			Position p2 = new Position();
			;
			player2filledPositions.add(p2);
			gameManagerGUI.getBoard().getPlayer1().setFilledPositions(player1filledPositions);
			gameManagerGUI.getBoard().getPlayer2().setFilledPositions(player2filledPositions);
		} else if (displayColor.equals(GameConstants.PLAYER2COLOR)) {
			List<Position> player1filledPositions = new ArrayList<>(2);
			List<Position> player2filledPositions = new ArrayList<>(3);
			Position p1 = new Position();
			player1filledPositions.add(p1);
			Position p2 = new Position();
			player1filledPositions.add(p2);
			gameManagerGUI.getBoard().getPlayer1().setFilledPositions(player1filledPositions);
			gameManagerGUI.getBoard().getPlayer2().setFilledPositions(player2filledPositions);
		}
		gameManagerGUI.getBoard().setPhase3Started(true);
		boolean gameEnded = gameEndService.decideGameEndCondition(gameManagerGUI.getBoard());
		assertTrue(gameEnded);
	}

	@Test
	public void noFurtherMoves() {
		// Setting up the board with only 3 players of one player and 2 players of one
		// player
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
		Color displayColor = (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
		if (displayColor.equals(GameConstants.PLAYER1COLOR)) {
			List<Position> player1filledPositions = new ArrayList<>(3);
			List<Position> player2filledPositions = new ArrayList<>(2);
			Position p1 = new Position();
			player2filledPositions.add(p1);
			Position p2 = new Position();
			;
			player2filledPositions.add(p2);
			gameManagerGUI.getBoard().getPlayer1().setFilledPositions(player1filledPositions);
			gameManagerGUI.getBoard().getPlayer2().setFilledPositions(player2filledPositions);
		} else if (displayColor.equals(GameConstants.PLAYER2COLOR)) {
			List<Position> player1filledPositions = new ArrayList<>(2);
			List<Position> player2filledPositions = new ArrayList<>(3);
			Position p1 = new Position();
			player1filledPositions.add(p1);
			Position p2 = new Position();
			player1filledPositions.add(p2);
			gameManagerGUI.getBoard().getPlayer1().setFilledPositions(player1filledPositions);
			gameManagerGUI.getBoard().getPlayer2().setFilledPositions(player2filledPositions);
		}

		gameManagerGUI.getBoard().setPhase3Started(true);
		if (gameEndService.decideGameEndCondition(gameManagerGUI.getBoard())) {
			gameEndService.prepareForGameEnd(gameManagerGUI.getBoard(), null);
		}
		assertTrue(player1PositionCircle3.isDisabled()); // player1 filled check
		assertTrue(player2PositionCircle1.isDisabled()); // player2 filled check
		PositionCircle blankPosition = from(rootNode).lookup("#position7").query();
		assertTrue(blankPosition.isDisabled()); // blank position check
	}

	@Test
	public void displayWinner() {
		// Setting up the board with only 3 players of one player and 2 players of one
		// player
		PositionCircle player1PositionCircle1 = from(rootNode).lookup("#position1").query();
		clickOn(player1PositionCircle1);
		PositionCircle player2PositionCircle1 = from(rootNode).lookup("#position2").query();
		clickOn(player2PositionCircle1);
		PositionCircle player1PositionCircle2 = from(rootNode).lookup("#position3").query();
		clickOn(player1PositionCircle2);
		PositionCircle player2PositionCircle2 = from(rootNode).lookup("#position14").query();
		clickOn(player2PositionCircle2);
		PositionCircle player1PositionCircle3 = from(rootNode).lookup("#position19").query();
		clickOn(player1PositionCircle3);
		Color displayColor = (Color) ((Circle) from(rootNode).lookup("#displayCircle").query()).getFill();
		if (displayColor.equals(GameConstants.PLAYER1COLOR)) {
			List<Position> player1filledPositions = new ArrayList<>(3);
			List<Position> player2filledPositions = new ArrayList<>(2);
			Position p1 = new Position();
			player2filledPositions.add(p1);
			Position p2 = new Position();
			;
			player2filledPositions.add(p2);
			gameManagerGUI.getBoard().getPlayer1().setFilledPositions(player1filledPositions);
			gameManagerGUI.getBoard().getPlayer2().setFilledPositions(player2filledPositions);
		} else if (displayColor.equals(GameConstants.PLAYER2COLOR)) {
			List<Position> player1filledPositions = new ArrayList<>(2);
			List<Position> player2filledPositions = new ArrayList<>(3);
			Position p1 = new Position();
			player1filledPositions.add(p1);
			Position p2 = new Position();
			player1filledPositions.add(p2);
			gameManagerGUI.getBoard().getPlayer1().setFilledPositions(player1filledPositions);
			gameManagerGUI.getBoard().getPlayer2().setFilledPositions(player2filledPositions);
		}
		gameManagerGUI.getBoard().setPhase3Started(true);
		if (gameEndService.decideGameEndCondition(gameManagerGUI.getBoard())) {
			gameEndService.prepareForGameEnd(gameManagerGUI.getBoard(), null);
		}
		String displayText = ((Text) (from(rootNode).lookup("#displayText").query())).getText();
		if (displayColor.equals(GameConstants.PLAYER1COLOR)) {
			assertEquals(displayText, GameConstants.PLAYER1WONTEXT); // player1 wins as player 2 has 2 coins
		} else if (displayColor.equals(GameConstants.PLAYER2COLOR)) {
			assertEquals(displayText, GameConstants.PLAYER2WONTEXT); // player2 wins as player 1 has 2 coins
		}
	}

}
