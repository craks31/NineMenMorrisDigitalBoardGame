package sprint1_0.product.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.controller.GameController;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.PositionCircle;

public class SixMenMorrisGameManagerGUI extends Application {

  Board board;
  private Scene scene;
  EventHandler<MouseEvent> resetGameButtonEventHandler;
  ExecutorService executorService;
  CommonGameManagerGUI commonGameManagerGUI = new CommonGameManagerGUI();

  public SixMenMorrisGameManagerGUI() {
    super();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {

    setScene(gameInit(primaryStage));
  }

  public static void main(String[] args) {
    launch(args);
  }

  private Group boardGUIInit(Board board) throws FileNotFoundException {
	  
	String filePath = ".\\resources\\images\\sixmen.png";

    Group boardGroup = commonGameManagerGUI.getOuterWindowUI(board, filePath);

    List<PositionCircle> positionCircleList = new ArrayList<>();

    List<Circle> player1Coins = new ArrayList<>();

    List<Circle> player2Coins = new ArrayList<>();

    // outermost circle
    PositionCircle o1c1r1 =
        new PositionCircle(175.0d, 175.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);
    o1c1r1.setId("position1");
    PositionCircle o1c1r2 =
        new PositionCircle(175.0d, 175.0d * 2, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o1c1r3 =
        new PositionCircle(175.0d, 175.0d * 3, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o1c2r1 =
        new PositionCircle(175.0d * 2, 175.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o1c2r3 =
        new PositionCircle(175.0d * 2, 175.0d * 3, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o1c3r1 =
        new PositionCircle(175.0d * 3, 175.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o1c3r2 =
        new PositionCircle(175.0d * 3, 175.0d * 2, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o1c3r3 =
        new PositionCircle(175.0d * 3, 175.0d * 3, 16.0d, GameConstants.BACKGROUNDCOLOR);

    positionCircleList.add(o1c1r1);
    positionCircleList.add(o1c1r2);
    positionCircleList.add(o1c1r3);
    positionCircleList.add(o1c2r1);
    positionCircleList.add(o1c2r3);
    positionCircleList.add(o1c3r1);
    positionCircleList.add(o1c3r2);
    positionCircleList.add(o1c3r3);

    PositionCircle o2c1r1 =
        new PositionCircle(235.0d, 235.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o2c1r2 =
        new PositionCircle(235.0d, 350.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o2c1r3 =
        new PositionCircle(235.0d, 465.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o2c2r1 =
        new PositionCircle(350.0d, 235.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o2c2r3 =
        new PositionCircle(350.0d, 465.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o2c3r1 =
        new PositionCircle(465.0d, 235.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o2c3r2 =
        new PositionCircle(465.0d, 350.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o2c3r3 =
        new PositionCircle(465.0d, 465.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);

    positionCircleList.add(o2c1r1);
    positionCircleList.add(o2c1r2);
    positionCircleList.add(o2c1r3);
    positionCircleList.add(o2c2r1);
    positionCircleList.add(o2c2r3);
    positionCircleList.add(o2c3r1);
    positionCircleList.add(o2c3r2);
    positionCircleList.add(o2c3r3);

    PositionCircle o3c1r1 =
        new PositionCircle(295.0d, 295.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o3c1r2 =
        new PositionCircle(295.0d, 350.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o3c1r3 =
        new PositionCircle(295.0d, 405.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o3c2r1 =
        new PositionCircle(350.0d, 295.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o3c2r3 =
        new PositionCircle(350.0d, 405.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o3c3r1 =
        new PositionCircle(405.0d, 295.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o3c3r2 =
        new PositionCircle(405.0d, 350.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);
    PositionCircle o3c3r3 =
        new PositionCircle(405.0d, 405.0d, 16.0d, GameConstants.BACKGROUNDCOLOR);

    positionCircleList.add(o3c1r1);
    positionCircleList.add(o3c1r2);
    positionCircleList.add(o3c1r3);
    positionCircleList.add(o3c2r1);
    positionCircleList.add(o3c2r3);
    positionCircleList.add(o3c3r1);
    positionCircleList.add(o3c3r2);
    positionCircleList.add(o3c3r3);
    for (int i = 0; i < positionCircleList.size(); i++) {
      String position = "position" + String.valueOf(i);
      positionCircleList.get(i).setId(position);
      positionCircleList.get(i).setDisable(true);
    }

    Group player1CoinsGroup = new Group();
    player1CoinsGroup.setId("player1CoinsGroup");
    Group player2CoinsGroup = new Group();
    player2CoinsGroup.setId("player2CoinsGroup");

    positionCircleList.forEach(boardGroup.getChildren()::add);
    for (int i = 0; i < 9; i++) {
      Circle c1;
      Circle c2;
      if (i < 5) {
        c1 = new Circle(25.0d + (i * 25), 200.0d, 10.0d, GameConstants.PLAYER1COLOR);
        c2 = new Circle(25.0d + (i * 25), 420.0d, 10.0d, GameConstants.PLAYER2COLOR);
      } else {
        c1 = new Circle(25.0d + ((i - 5) * 25), 225.0d, 10.0d, GameConstants.PLAYER1COLOR);
        c2 = new Circle(25.0d + ((i - 5) * 25), 445.0d, 10.0d, GameConstants.PLAYER2COLOR);
      }
      player1Coins.add(c1);
      player2Coins.add(c2);
    }

    player1Coins.forEach(player1CoinsGroup.getChildren()::add);
    boardGroup.getChildren().add(player1CoinsGroup);
    player2Coins.forEach(player2CoinsGroup.getChildren()::add);
    boardGroup.getChildren().add(player2CoinsGroup);
    board.setPositionCircleList(positionCircleList);
    board.getPlayer1().setCoins(player1Coins);
    board.getPlayer1().setPlayerColor(GameConstants.PLAYER1COLOR);
    board.getPlayer2().setCoins(player2Coins);
    board.getPlayer2().setPlayerColor(GameConstants.PLAYER2COLOR);

    return boardGroup;
  }

  public Scene gameInit(Stage primaryStage) throws Exception {
    GameController gameController = new GameController();

    // Get a New Fresh Board Object
    board = gameController.getNewBoard();

    // Designing the board GUI
    Scene scene = new Scene(boardGUIInit(board), 700, 600);
    scene =
        commonGameManagerGUI.gameInit(
            primaryStage, "Nine Men Morris Digital Board Game", board, scene);
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
    board.getResetGameButton().setOnMouseClicked(resetGameButtonEventHandler);
    return scene;
  }

  public Board getBoard() {
    return board;
  }

  public Scene getScene() {
    return scene;
  }

  public void setScene(Scene scene) {
    this.scene = scene;
  }
}
