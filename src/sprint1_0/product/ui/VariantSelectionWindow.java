package sprint1_0.product.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VariantSelectionWindow extends Application {

  private static boolean launched = false;

  public static void main(String[] args) {
    if (!launched) {
      launch(args);
      launched = true;
    }
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Select Game Variant");

    Button nineMenMorrisButton = new Button("Nine Men Morris");
    nineMenMorrisButton.setOnAction(
        event -> {
          try {
            openNineMenMorrisWindow();
            primaryStage.close();
          } catch (Exception e) {
            
            e.printStackTrace();
          }
        });

    Button sixMenMorrisButton = new Button("Six Men Morris");
    sixMenMorrisButton.setOnAction(
        event -> {
          try {
            openSixMenMorrisWindow();
            primaryStage.close();

          } catch (Exception e) {
            
            e.printStackTrace();
          }
        });

    VBox root = new VBox(10, nineMenMorrisButton,sixMenMorrisButton );
    Scene scene = new Scene(root, 300, 200);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void openNineMenMorrisWindow() throws Exception {
    // Launch the NineMenMorris window
    new NineMenMorrisGameManagerGUI().start(new Stage());
  }

  private void openSixMenMorrisWindow() throws Exception {
    // Launch the NineMenMorris window
    new SixMenMorrisGameManagerGUI().start(new Stage());
  }
}
