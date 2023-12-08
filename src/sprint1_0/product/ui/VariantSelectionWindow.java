package sprint1_0.product.ui;

import javafx.application.Application;
import javafx.scene.Scene;
// import javafx.geometry.Pos;
import javafx.scene.control.Button;
// import javafx.scene.layout.GridPane;
// import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
    nineMenMorrisButton.setLayoutX(100);
    nineMenMorrisButton.setLayoutY(150);
    nineMenMorrisButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
    nineMenMorrisButton.setOnMouseEntered(
        e -> nineMenMorrisButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;"));
    nineMenMorrisButton.setOnMouseExited(
        e -> nineMenMorrisButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;"));
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
    sixMenMorrisButton.setLayoutX(350);
    sixMenMorrisButton.setLayoutY(150);
    sixMenMorrisButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
    sixMenMorrisButton.setOnMouseEntered(
        e -> sixMenMorrisButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;"));
    sixMenMorrisButton.setOnMouseExited(
        e -> sixMenMorrisButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;"));
    sixMenMorrisButton.setOnAction(
        event -> {
          try {
            openSixMenMorrisWindow();
            primaryStage.close();
          } catch (Exception e) {

            e.printStackTrace();
          }
        });
    Text selectionText = new Text();
    selectionText.setText("Which Game You want to Play?");
    selectionText.setX(100);
    selectionText.setY(80);
    selectionText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
    selectionText.setFill(javafx.scene.paint.Color.BLACK);

    Pane root = new Pane();
    root.setStyle("-fx-background-color:LIGHTBLUE");
    root.getChildren().addAll(selectionText, nineMenMorrisButton, sixMenMorrisButton);

    //    HBox root = new HBox(10,nineMenMorrisButton,sixMenMorrisButton );
    //    root.setAlignment(Pos.CENTER);
    Scene scene = new Scene(root, 600, 400);

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
