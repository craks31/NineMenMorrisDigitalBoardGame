package sprint1_0.acceptancetest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.model.PositionCircle;
import sprint1_0.product.ui.GameManagerGUI;

public class BoardVisualisationTest extends ApplicationTest{
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

    @Test
    public void hasNewGameButton() {
        Button button = from(rootNode).lookup("#newGameButton").query();
        assertEquals("NEW GAME", button.getText());
    }
    
    @Test
    public void hasDecideButton() {
        Button button = from(rootNode).lookup("#decideButton").query();
        assertEquals("DECIDE", button.getText());        
    }
    // hasresetbutton
    
    @Test
    public void isDecidedButtonDisabled() {
        
        Button button = from(rootNode).lookup("#decideButton").query();
        assertEquals(true, button.isDisabled());
    }
    
    //isresetButtondisabled
    
    //isnewbuttonenabled
    
    @Test
    public void onClickofNewButtonIsDecidedButtonEnabled() {
    	 Button newGameButton = from(rootNode).lookup("#newGameButton").query();
         Button decideButton = from(rootNode).lookup("#decideButton").query();
         clickOn(newGameButton);
         assertEquals(false, decideButton.isDisabled());
    }
    
    //onClickofNewButtonIsNewButtonDisbled()
    
  //onClickofNewButtonIsResetButtonEnabled()
    
    //checkDisplayText
    
    //checkDisplayCircle
    
    @Test
    public void checkifAllPositionsAreAvailableAndNotClickable() {
    	for(int i=0; i<GameConstants.NUM_POSITIONS_OF_BOARD;i++) {
    		String pos = "#position"+String.valueOf(i);
         PositionCircle c = from(rootNode).lookup(pos).query();
         clickOn(c);
         assertEquals(false, c.isDisabled());
    	}
    }
    
    
}