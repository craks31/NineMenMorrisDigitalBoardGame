package sprint1_0.product.service;

import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Scene;
import sprint1_0.product.ui.NineMenMorrisGameManagerGUI;

public class ComputerAutomaticService extends ApplicationTest {
  private NineMenMorrisGameManagerGUI gameManagerGUI = new NineMenMorrisGameManagerGUI();

  public Scene getRootNode() {
    return gameManagerGUI.getScene();
  }
}
