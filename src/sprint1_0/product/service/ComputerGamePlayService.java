package sprint1_0.product.service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;

import javafx.application.Platform;
import javafx.concurrent.Task;
import sprint1_0.acceptancetest.CoinPlacementTest;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;
import sprint1_0.product.model.PositionCircle;

public class ComputerGamePlayService {
  private Board board;

  public void makeComputerFill(List<Position> positionCircleList, ExecutorService executorService) {

    Task<Void> computerMoveTask =
        new Task<Void>() {
          @Override
          protected Void call() throws Exception {
            Random random = new Random();
            int randomIndex = random.nextInt(positionCircleList.size());
            PositionCircle c = positionCircleList.get(randomIndex).getPositionCircle();
            CoinPlacementTest ct = new CoinPlacementTest();
            System.out.println(
                "Computer player makes a move on thread: "
                    + Thread.currentThread().getName()
                    + " at Position:  "
                    + c.getId());

            try {
              Thread.sleep(1500);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            ct.clickOn(c);

            return null;
          }
        };

    executorService.submit(computerMoveTask);
  }

  public Position makeComputerMove(
      List<Position> positionCircleList, ExecutorService executorService) {
    Position clickedPosition = null;
    Task<Position> computerMoveTask =
        new Task<Position>() {
          @Override
          protected Position call() throws Exception {
            Random random = new Random();
            int randomIndex = random.nextInt(positionCircleList.size());
            PositionCircle c = positionCircleList.get(randomIndex).getPositionCircle();
            CoinPlacementTest ct = new CoinPlacementTest();
            System.out.println(
                "Computer player makes a move on thread: "
                    + Thread.currentThread().getName()
                    + " at Position:  "
                    + randomIndex);

            try {
              Thread.sleep(1500);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            ct.clickOn(c);

            return positionCircleList.get(randomIndex);
          }
        };

    // Bind the execution of the Task to the JavaFX Application Thread
    computerMoveTask.setOnSucceeded(
        event -> {
          Platform.runLater(
              () -> {
                // Update UI components here after the computer move
                System.out.println("UI updated after computer move");
              });
        });

    executorService.submit(computerMoveTask);
    return null;
  }

  public void makeComputerRemove(List<Position> positionCircleList) {
    Random random = new Random();
    int randomIndex = random.nextInt(positionCircleList.size());
    PositionCircle c = positionCircleList.get(randomIndex).getPositionCircle();
    CoinPlacementTest ct = new CoinPlacementTest();

    Platform.runLater(
        () -> {
          try {
            Thread.sleep(1200);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          ct.clickOn(c);
        });
  }

  public void makeComputerFly() {}

  public void setBoard(Board board) {
    this.board = board;
  }

  public void simulateComputerClick(ExecutorService executorService, Board board) {
    this.board = board;
    // CompletableFuture.runAsync(this::simulateComputerFill, executorService);
  }

  //  private void simulateComputerFill() {
  //    makeComputerFill(this.board.getBlankPositionList());
  //  }
}
