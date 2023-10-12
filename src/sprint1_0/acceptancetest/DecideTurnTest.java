package sprint1_0.acceptancetest;

import org.junit.After;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sprint1_0.product.ui.GameManagerGUI;

public class DecideTurnTest extends ApplicationTest{
	
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
		
		//onclick decide disable
		
		//onclick text and circle change
		
		//onclick pos enable
		
		
		
}
