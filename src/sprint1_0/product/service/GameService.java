package sprint1_0.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.helper.DisplayUtil;
import sprint1_0.product.helper.PositionHelper;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;
import sprint1_0.product.model.PositionCircle;

public class GameService {
  DisplayUtil displayUtil = new DisplayUtil();
  CoinPlacementService coinPlacementService = new CoinPlacementService();
  CoinMovementService coinMovementService = new CoinMovementService();
  CoinRemovalService coinRemovalService = new CoinRemovalService();
  CoinFlyService coinFlyService = new CoinFlyService();
  ComputerGamePlayService compGamePlayService = new ComputerGamePlayService();

  /**
   * This method is used to deicde a player turn randomly.
   *
   * @param board
   * @return
   */
  public Color decidePlayerColorTurn(Board board) {
    Random rand = new Random();
    boolean isPlayer1 = rand.nextBoolean();
    if (isPlayer1) {
      return board.getPlayer1().getPlayerColor();
    } else {
      return board.getPlayer2().getPlayerColor();
    }
  }

  public void initDisplayAndStartGame(
      Board board, Color decidedColor, ExecutorService executorService, Stage stage) {

    EventHandler<javafx.scene.input.MouseEvent> decideButtonEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {

          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            displayUtil.displayPlayerTurn(board, decidedColor);
            board.getDecideButton().setDisable(true);
            placeCoinsSetup(board, executorService, stage);
            board.getRecordGameButton().setDisable(false);
            recordGame(board);
            if (board.isPlayer2Computer() && decidedColor.equals(GameConstants.PLAYER2COLOR)) {
              compGamePlayService.makeComputerFill(board.getBlankPositionList(), executorService);
            }
          }
        };

    // Adding the event handler
    board.getDecideButton().setOnMouseClicked(decideButtonEventHandler);
  }

  protected void playComputerGame(Board board, Color decideColor) {
    if (decideColor.equals(GameConstants.PLAYER2COLOR)) {
      // 1st Computer Turn
    } else {

    }
  }

  public void startGame(
      Board board, Color decideColor, ExecutorService executorService, Stage stage) {
    initDisplayAndStartGame(board, decideColor, executorService, stage);
  }

  public void placeCoinsSetup(Board board, ExecutorService executorService, Stage stage) {
    board.setOp("FILL");
    for (int i = 0; i < board.getBlankPositionList().size(); i++) {
      PositionCircle positionCircle = board.getBlankPositionList().get(i).getPositionCircle();
      // Adding the event handler
      positionCircle.setDisable(false);
      positionCircle.addEventHandler(
          MouseEvent.MOUSE_CLICKED, coinMouseEventHandler(board, executorService, stage));
      positionCircle.setUserData(i);
    }
  }

  private EventHandler<MouseEvent> coinMouseEventHandler(
      Board board, ExecutorService executorService, Stage stage) {

    EventHandler<MouseEvent> coinMouseEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {

          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            Circle clickedCircle = (Circle) e.getSource();
            if (board.isRecordingEnabled()) {
              board.getRecordReplayService().captureScreen(stage, board);
            }
            if (board.getOp().equals("FILL")) {
              coinPlacementService.coinFillEvent(board, clickedCircle, executorService);
              if (checkMill(
                      board,
                      board.getAllPositionList().get((int) clickedCircle.getUserData()),
                      clickedCircle.getFill())
                  && !board.isMillCheckByPassed()) {
                coinRemovalService.prepareForCoinRemoval(
                    board, clickedCircle.getFill(), executorService);
              } else if (board.isPlayer2Computer()
                  && board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER2COLOR)
                  && !board.getPlayer2().getCoins().isEmpty()
                  && !board.isPhase2Started()) {
                System.out.println("DEBUG -- Initiating computer Fill after Human Fill in phase1");
                board
                    .getBlankPositionList()
                    .remove(board.getAllPositionList().get((int) clickedCircle.getUserData()));
                compGamePlayService.makeComputerFill(board.getBlankPositionList(), executorService);
              } else if (board.isPlayer2Computer()
                  && board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER2COLOR)
                  && board.getOp().equals("MOVE")) {
                System.out.println("DEBUG -- Initiating computer Move after Human Fill in phase2 ");
                compGamePlayService.makeComputerFill(
                    coinMovementService.findComputerCoinsWithEmptyNeighbors(
                        board, PositionHelper.getPlayer2FilledCoins(board.getAllPositionList())),
                    executorService);
              } else if (board.isPlayer2Computer()
                  && board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER2COLOR)
                  && board.getOp().equals("FLY")) {
                System.out.println("DEBUG -- Initiating computer Fly after Human Fill in phase3 ");
                compGamePlayService.makeComputerFill(
                    PositionHelper.getPlayer2FilledCoins(board.getAllPositionList()),
                    executorService);
              }

            } else if (board.getOp().equals("MOVE")) {
              coinMovementService.coinMoveEvent(board, clickedCircle, executorService);
              board.setPhase2Started(true);
              clickedCircle.setDisable(true);
              if (board.isPlayer2Computer()
                  && board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER2COLOR)) {
                System.out.println(
                    "Player 2 Coin has Neighbors - : "
                        + board.getEmptyNeighborsForComputer().size());
                for (Position p : board.getEmptyNeighborsForComputer()) {
                  System.out.println(p.getPositionCircle().getId());
                }
                System.out.println(
                    "DEBUG -- Initiating computer Fill after computer move in phase2");
                compGamePlayService.makeComputerFill(
                    board.getEmptyNeighborsForComputer(), executorService);
                board.setOp("FILL");
              }
            } else if (board.getOp().equals("REMOVE")) {
              coinRemovalService.coinRemoveEvent(board, clickedCircle, executorService, stage);
          //    clickedCircle.setDisable(false);
            } else if (board.getOp().equals("FLY")) {
              coinFlyService.coinFlyEvent(board, clickedCircle);
              if (board.isPlayer2Computer()
                  && board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER2COLOR)
                  && board.isPhase3Started()
                  && board.isPhase3Started()) {
                System.out.println(
                    "DEBUG -- Initiating computer Fill after computer Fly in phase3");
                compGamePlayService.makeComputerFill(board.getBlankPositionList(), executorService);
              }
            }
          }
        };
    return coinMouseEventHandler;
  }

  private boolean checkMill(Board board, Position position, Paint color) {
    boolean millFormed = false;
    int horizontalMillDecider = 0;
    int verticalMillDecider = 0;
    boolean leftDecider = false;
    boolean rightDecider = false;
    boolean upDecider = false;
    boolean downDecider = false;
    List<Position> horMillList = new ArrayList<>();
    List<Position> verMillList = new ArrayList<>();
    horMillList.add(position);
    verMillList.add(position);
    if (position.getLeft() != null
        && position.getLeft().isFilled()
        && position.getLeft().getFill().equals(color)) {
      horizontalMillDecider++;
      horMillList.add(position.getLeft());
      leftDecider = true;
    }
    if (position.getUp() != null
        && position.getUp().isFilled()
        && position.getUp().getFill().equals(color)) {
      verticalMillDecider++;
      verMillList.add(position.getUp());
      upDecider = true;
    }
    if (position.getRight() != null
        && position.getRight().isFilled()
        && position.getRight().getFill().equals(color)) {
      horizontalMillDecider++;
      horMillList.add(position.getRight());
      rightDecider = true;
    }
    if (position.getDown() != null
        && position.getDown().isFilled()
        && position.getDown().getFill().equals(color)) {
      verticalMillDecider++;
      verMillList.add(position.getDown());
      downDecider = true;
    }

    if (horizontalMillDecider == 1) {
      if (leftDecider) {
        if (position.getLeft().getLeft() != null
            && position.getLeft().getLeft().isFilled()
            && position.getLeft().getLeft().getFill().equals(color)) {
          horizontalMillDecider++;
          horMillList.add(position.getLeft().getLeft());
        }
      } else if (rightDecider) {
        if (position.getRight().getRight() != null
            && position.getRight().getRight().isFilled()
            && position.getRight().getRight().getFill().equals(color)) {
          horizontalMillDecider++;
          horMillList.add(position.getRight().getRight());
        }
      }

    } else if (verticalMillDecider == 1) {
      if (upDecider) {
        if (position.getUp().getUp() != null
            && position.getUp().getUp().isFilled()
            && position.getUp().getUp().getFill().equals(color)) {
          verticalMillDecider++;
          verMillList.add(position.getUp().getUp());
        }
      } else if (downDecider) {
        if (position.getDown().getDown() != null
            && position.getDown().getDown().isFilled()
            && position.getDown().getDown().getFill().equals(color)) {
          verticalMillDecider++;
          verMillList.add(position.getDown().getDown());
        }
      }
    }
    if (horizontalMillDecider == 2) {
      System.out.println("Horizontal Mill Formed");
      millFormed = true;
      horMillList.forEach(
          p -> {
            p.setPartOfHorMill(true);
            p.setHorMillFamily(horMillList);
          });
    }
    if (verticalMillDecider == 2) {
      System.out.println("Vertical Mill Formed");
      millFormed = true;
      verMillList.forEach(
          p -> {
            p.setPartOfVerMill(true);
            p.setVerMillFamily(verMillList);
          });
    }
    System.out.println("Hor" + horizontalMillDecider);
    System.out.println("Ver" + verticalMillDecider);
    return millFormed;
  }

  /**
   * This method is used to RESET the board when RESET button is clicked
   *
   * @param board
   */
  public void resetGame(Board board) {
    EventHandler<javafx.scene.input.MouseEvent> resetGameButtonEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {

          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            board.getDecideButton().setDisable(true);
            board.getStartNewGameButton().setDisable(false);
            board.getResetGameButton().setDisable(true);
            board.getDisplayTextTurn().setText("Who's Turn?");
            board.getDisplayCircleTurn().setFill(Color.BLACK);
            for (int i = 0; i < board.getPositionCircleList().size(); i++) {
              board.getPositionCircleList().get(i).setFill(Color.ROSYBROWN);
              board.getPositionCircleList().get(i).setDisable(true);
            }
          }
        };
    board.getResetGameButton().setOnMouseClicked(resetGameButtonEventHandler);
  }

  /**
   * * This method is used to setup the NEW board GUI when NEW GAME BUTTON IS CLICKED
   *
   * @param board
   */
  public void recordGame(Board board) {
    EventHandler<javafx.scene.input.MouseEvent> recordButtonEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {
          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            board.setRecordingEnabled(true);
            board.setRecordReplayService(new RecordReplayService());
            board.getRecordGameButton().setDisable(true);
          }
        };
    board.getRecordGameButton().setOnMouseClicked(recordButtonEventHandler);
  }
}
