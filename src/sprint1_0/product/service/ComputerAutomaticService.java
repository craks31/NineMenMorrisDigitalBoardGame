package sprint1_0.product.service;

import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Scene;
import sprint1_0.product.ui.GameManagerGUI;

public class ComputerAutomaticService extends ApplicationTest {
  private GameManagerGUI gameManagerGUI = new GameManagerGUI();

  public Scene getRootNode() {
    return gameManagerGUI.getScene();
  }
}
