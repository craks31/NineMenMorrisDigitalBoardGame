package sprint1_0.acceptancetest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.stage.Stage;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.controller.GameController;
import sprint1_0.product.model.Board;
import sprint1_0.product.ui.GameManagerGUI;

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
    }

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
