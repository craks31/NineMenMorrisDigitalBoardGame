package sprint1_0.product.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.controller.GameController;
import sprint1_0.product.model.Board;

public class CommonGameManagerGUI {
  private Button decideButton;
  private Button startNewGameButton;
  private Button resetGameButton;
  private Button recordGameButton;
  private Button replayGameButton;
  private Button frontButton;
  private Button backwardButton;
  private Button forwardButton;
  private Button endButton;
  ExecutorService executorService;
  private Scene gameScene;
  private Stage gameInitStage;

  public Group getOuterWindowUI(Board board, String filePath) throws FileNotFoundException {

    startNewGameButton = new Button("NEW GAME");

    startNewGameButton.setLayoutX(15);
    startNewGameButton.setLayoutY(280);

    resetGameButton = new Button("RESET GAME");
    resetGameButton.setLayoutX(15);
    resetGameButton.setLayoutY(320);
    resetGameButton.setDisable(true);

    recordGameButton = new Button("RECORD");
    recordGameButton.setLayoutX(590);
    recordGameButton.setLayoutY(280);
    recordGameButton.setDisable(true);

    replayGameButton = new Button("REPLAY ");
    replayGameButton.setLayoutX(590);
    replayGameButton.setLayoutY(350);
    replayGameButton.setDisable(true);

    frontButton = new Button("       <<       ");
    frontButton.setLayoutX(170);
    frontButton.setLayoutY(565);
    frontButton.setDisable(true);

    backwardButton = new Button("       <       ");
    backwardButton.setLayoutX(268);
    backwardButton.setLayoutY(565);
    backwardButton.setDisable(true);

    forwardButton = new Button("       >       ");
    forwardButton.setLayoutX(356);
    forwardButton.setLayoutY(565);
    forwardButton.setDisable(true);

    endButton = new Button("      >>      ");
    endButton.setLayoutX(444);
    endButton.setLayoutY(565);
    endButton.setDisable(true);

    decideButton = new Button("DECIDE");
    decideButton.setLayoutX(50);
    decideButton.setLayoutY(55);
    decideButton.setDisable(true);

    Text displayText = new Text();
    displayText.setText("Who's Turn ?");
    displayText.setX(200);
    displayText.setY(80);
    displayText.setFont(Font.font(30));
    Circle displayCircle = new Circle(170.0d, 70.0d, 16.0d, GameConstants.DISPLAYCIRCLECOLOR);
    Image myImage = new Image(new FileInputStream(filePath));

    // Setting the image view
    ImageView imageView = new ImageView(myImage);

    imageView.setX(145);
    imageView.setY(145);

    // setting the fit height and width of the image view
    imageView.setFitHeight(410);
    imageView.setFitWidth(410);

    // Setting the preserve ratio of the image view
    imageView.setPreserveRatio(true);
    imageView.setId("imageView");

    Text player1Text = new Text();
    player1Text.setText("PLAYER 1");
    player1Text.setX(25);
    player1Text.setY(180);

    Text player2Text = new Text();
    player2Text.setText("PLAYER 2");
    player2Text.setX(25);
    player2Text.setY(400);
    
    Text recordText = new Text();
    recordText.setText("");
    recordText.setX(600);
    recordText.setY(60);
    recordText.setFont(Font.font(20));
    recordText.setId("recordText");
    
    Text plainText = new Text();
    plainText.setText("");
    plainText.setX(160);
    plainText.setY(40);
    plainText.setFont(Font.font(20));
    plainText.setId("plainText");

    Group boardGroup =
        new Group(
            player1Text,
            player2Text,
            startNewGameButton,
            resetGameButton,
            decideButton,
            recordGameButton,
            replayGameButton,
            frontButton,
            backwardButton,
            forwardButton,
            endButton,
            plainText,
            recordText,
            displayCircle,
            displayText,
            imageView);

    decideButton.setId("decideButton");
    startNewGameButton.setId("newGameButton");
    resetGameButton.setId("resetGameButton");
    recordGameButton.setId("recordGameButton");
    replayGameButton.setId("replayGameButton");
    forwardButton.setId("forwardGameButton");
    backwardButton.setId("backwardButton");
    frontButton.setId("frontButton");
    endButton.setId("endButton");
    displayCircle.setId("displayCircle");
    displayText.setId("displayText");
    player1Text.setId("player1Text");
    player2Text.setId("player2Text");
    board.setDisplayTextTurn(displayText);
    board.setDisplayCircleTurn(displayCircle);
    board.setDecideButton(decideButton);
    board.setStartNewGameButton(startNewGameButton);
    board.setResetGameButton(resetGameButton);
    board.setRecordGameButton(recordGameButton);
    board.setReplayGameButton(replayGameButton);
    board.setForwardButton(forwardButton);
    board.setBackwardButton(backwardButton);
    board.setFrontButton(frontButton);
    board.setEndButton(endButton);
    board.setDisplayPlainText(plainText);
    return boardGroup;
  }

  public Scene gameInit(Stage primaryStage, String title, Board board, Scene scene, String variant)
      throws Exception {
    GameController gameController = new GameController();

    // Setting title to Window Pop-up.
    primaryStage.setTitle("Nine Men Morris Digital Board Game");

    // setting color to the scene
    scene.setFill(GameConstants.BACKGROUNDCOLOR);

    primaryStage.setScene(scene);
    primaryStage.show();

    // Initialize board GUI to implement business logic
    gameController.init(board, variant);

    // New Game Initialization
    gameController.newGame(board, primaryStage);

    // Decide PlayerTurn
    Color decidedColor = gameController.decidePlayerTurn(board);

    this.executorService = Executors.newSingleThreadExecutor();

    // Start Game After Deciding
    gameController.startGame(board, decidedColor, executorService, primaryStage);

    return scene;
  }

  public Scene getGameScene() {
    return gameScene;
  }

  public void setGameScene(Scene gameScene) {
    this.gameScene = gameScene;
  }

  public Stage getGameInitStage() {
    return gameInitStage;
  }

  public void setGameInitStage(Stage gameInitStage) {
    this.gameInitStage = gameInitStage;
  }
}
