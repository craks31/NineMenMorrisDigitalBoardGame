package sprint1_0.acceptancetest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.controller.GameController;
import sprint1_0.product.model.Board;

public class CoinPlacementTest {
	
	private Board board;
	private GameController gameController;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
		gameController = new GameController();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSuccessfulCoinPlacementonEmptyCell() {
	gameController.startGame(board, GameConstants.PLAYER1COLOR);
	assertEquals("", 1, 1);
		
	}
}
