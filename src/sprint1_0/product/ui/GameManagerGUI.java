package sprint1_0.product.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sprint1_0.product.controller.GameController;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.PositionCircle;

public class GameManagerGUI extends Application {

  public static final Color player1Color = Color.CORNFLOWERBLUE;
  public static final Color player2Color = Color.GREEN;
  Button decideButton;
  Button startNewGameButton;
  Button resetGameButton;
  EventHandler<MouseEvent> resetGameButtonEventHandler;

  public GameManagerGUI() {
    super();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {

    gameInit(primaryStage);
  }

  public static void main(String[] args) {
    launch(args);
  }

  private Group boardGUIInit(Board board) throws FileNotFoundException {

    startNewGameButton = new Button("NEW GAME");

    startNewGameButton.setLayoutX(15);
    startNewGameButton.setLayoutY(280);

    resetGameButton = new Button("RESET GAME");

    resetGameButton.setLayoutX(15);
    resetGameButton.setLayoutY(320);
    resetGameButton.setDisable(true);

    decideButton = new Button("DECIDE");

    decideButton.setLayoutX(480);
    decideButton.setLayoutY(55);
    decideButton.setDisable(true);

    Text displayText = new Text();
    displayText.setText("Who's Turn ?");
    displayText.setX(230);
    displayText.setY(80);
    displayText.setFont(Font.font(30));
    Circle displayCircle = new Circle(185.0d, 70.0d, 16.0d, Color.BLACK);
    Image myImage = new Image(new FileInputStream(".\\resources\\images\\nmbg.png"));

    // Setting the image view
    ImageView imageView = new ImageView(myImage);

    imageView.setX(145);
    imageView.setY(145);

    // setting the fit height and width of the image view
    imageView.setFitHeight(410);
    imageView.setFitWidth(410);

    // Setting the preserve ratio of the image view
    imageView.setPreserveRatio(true);

    List<PositionCircle> positionCircleList = new ArrayList<>();

    List<Circle> player1Coins = new ArrayList<>();

    List<Circle> player2Coins = new ArrayList<>();

    // outermost circle
    PositionCircle o1c1r1 = new PositionCircle(175.0d, 175.0d, 16.0d, Color.ROSYBROWN);
    PositionCircle o1c1r2 = new PositionCircle(175.0d, 175.0d * 2, 16.0d, Color.ROSYBROWN);
    PositionCircle o1c1r3 = new PositionCircle(175.0d, 175.0d * 3, 16.0d, Color.ROSYBROWN);
    PositionCircle o1c2r1 = new PositionCircle(175.0d * 2, 175.0d, 16.0d, Color.ROSYBROWN);
    PositionCircle o1c2r3 = new PositionCircle(175.0d * 2, 175.0d * 3, 16.0d, Color.ROSYBROWN);
    PositionCircle o1c3r1 = new PositionCircle(175.0d * 3, 175.0d, 16.0d, Color.ROSYBROWN);
    PositionCircle o1c3r2 = new PositionCircle(175.0d * 3, 175.0d * 2, 16.0d, Color.ROSYBROWN);
    PositionCircle o1c3r3 = new PositionCircle(175.0d * 3, 175.0d * 3, 16.0d, Color.ROSYBROWN);

    positionCircleList.add(o1c1r1);
    positionCircleList.add(o1c1r2);
    positionCircleList.add(o1c1r3);
    positionCircleList.add(o1c2r1);
    positionCircleList.add(o1c2r3);
    positionCircleList.add(o1c3r1);
    positionCircleList.add(o1c3r2);
    positionCircleList.add(o1c3r3);

    PositionCircle o2c1r1 = new PositionCircle(235.0d, 235.0d, 16.0d, Color.ROSYBROWN);
    PositionCircle o2c1r2 = new PositionCircle(235.0d, 350.0d, 16.0d, Color.ROSYBROWN);
    PositionCircle o2c1r3 = new PositionCircle(235.0d, 465.0d, 16.0d, Color.ROSYBROWN);
    PositionCircle o2c2r1 = new PositionCircle(350.0d, 235.0d, 16.0d, Color.ROSYBROWN);
    PositionCircle o2c2r3 = new PositionCircle(350.0d, 465.0d, 16.0d, Color.ROSYBROWN);
    PositionCircle o2c3r1 = new PositionCircle(465.0d, 235.0d, 16.0d, Color.ROSYBROWN);
    PositionCircle o2c3r2 = new PositionCircle(465.0d, 350.0d, 16.0d, Color.ROSYBROWN);
    PositionCircle o2c3r3 = new PositionCircle(465.0d, 465.0d, 16.0d, Color.ROSYBROWN);

    positionCircleList.add(o2c1r1);
    positionCircleList.add(o2c1r2);
    positionCircleList.add(o2c1r3);
    positionCircleList.add(o2c2r1);
    positionCircleList.add(o2c2r3);
    positionCircleList.add(o2c3r1);
    positionCircleList.add(o2c3r2);
    positionCircleList.add(o2c3r3);

    PositionCircle o3c1r1 = new PositionCircle(295.0d, 295.0d, 16.0d, Color.ROSYBROWN);
    PositionCircle o3c1r2 = new PositionCircle(295.0d, 350.0d, 16.0d, Color.ROSYBROWN);
    PositionCircle o3c1r3 = new PositionCircle(295.0d, 405.0d, 16.0d, Color.ROSYBROWN);
    PositionCircle o3c2r1 = new PositionCircle(350.0d, 295.0d, 16.0d, Color.ROSYBROWN);
    PositionCircle o3c2r3 = new PositionCircle(350.0d, 405.0d, 16.0d, Color.ROSYBROWN);
    PositionCircle o3c3r1 = new PositionCircle(405.0d, 295.0d, 16.0d, Color.ROSYBROWN);
    PositionCircle o3c3r2 = new PositionCircle(405.0d, 350.0d, 16.0d, Color.ROSYBROWN);
    PositionCircle o3c3r3 = new PositionCircle(405.0d, 405.0d, 16.0d, Color.ROSYBROWN);

    positionCircleList.add(o3c1r1);
    positionCircleList.add(o3c1r2);
    positionCircleList.add(o3c1r3);
    positionCircleList.add(o3c2r1);
    positionCircleList.add(o3c2r3);
    positionCircleList.add(o3c3r1);
    positionCircleList.add(o3c3r2);
    positionCircleList.add(o3c3r3);
    Text text1 = new Text();
    text1.setText("PLAYER 1");
    text1.setX(25);
    text1.setY(180);

    Text text2 = new Text();
    text2.setText("PLAYER 2");
    text2.setX(25);
    text2.setY(380);

    Group boardGroup =
        new Group(
            text1,
            text2,
            startNewGameButton,
            resetGameButton,
            decideButton,
            displayCircle,
            displayText,
            imageView);
    positionCircleList.forEach(boardGroup.getChildren()::add);
    for (int i = 0; i < 9; i++) {
      Circle c1;
      Circle c2;
      if (i < 5) {
        c1 = new Circle(25.0d + (i * 25), 200.0d, 10.0d, player1Color);
        c2 = new Circle(25.0d + (i * 25), 400.0d, 10.0d, player2Color);
      } else {
        c1 = new Circle(25.0d + ((i - 5) * 25), 225.0d, 10.0d, player1Color);
        c2 = new Circle(25.0d + ((i - 5) * 25), 425.0d, 10.0d, player2Color);
      }
      player1Coins.add(c1);
      player2Coins.add(c2);
    }
    player1Coins.forEach(boardGroup.getChildren()::add);
    player2Coins.forEach(boardGroup.getChildren()::add);
    board.setPositionCircleList(positionCircleList);
    board.getPlayer1().setCoins(player1Coins);
    board.getPlayer1().setPlayerColor(player1Color);
    board.getPlayer2().setCoins(player2Coins);
    board.getPlayer2().setPlayerColor(player1Color);
    board.setDisplayTextTurn(displayText);
    board.setDisplayCircleTurn(displayCircle);
    board.setDecideButton(decideButton);
    board.setStartNewGameButton(startNewGameButton);
    board.setResetGameButton(resetGameButton);
    return boardGroup;
  }

  public void displayPlayerTurn(Board board, Color color) {
    if (color.equals(player1Color)) {
      board.getDisplayCircleTurn().setFill(color);
      board.getDisplayTextTurn().setText("PLAYER 1'S TURN");
    } else {
      board.getDisplayCircleTurn().setFill(color);
      board.getDisplayTextTurn().setText("PLAYER 2'S TURN");
    }
  }

  public void gameInit(Stage primaryStage) throws Exception {
    GameController gameController = new GameController();

    // Get a New Fresh Board Object
    Board board = gameController.getNewBoard();

    // Setting title to Window Pop-up.
    primaryStage.setTitle("Nine Men Morris Digital Board Game");

    // Designing the board GUI
    Scene scene = new Scene(boardGUIInit(board), 700, 600);

    // setting color to the scene
    scene.setFill(Color.ROSYBROWN);

    primaryStage.setScene(scene);
    primaryStage.show();

    // Initialize board GUI to implement business logic
    gameController.init(board);

    // New Game Initialization
    gameController.newGame(board);

    // Decide PlayerTurn
    Color decidedColor = gameController.decidePlayerTurn(board);

    // Start Game After Deciding
    gameController.startGame(board, decidedColor);

    resetGameButtonEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {

          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            try {

              gameInit(primaryStage);

            } catch (Exception e1) {
              e1.printStackTrace();
            }
          }
        };
    resetGameButton.setOnMouseClicked(resetGameButtonEventHandler);
  }
}
