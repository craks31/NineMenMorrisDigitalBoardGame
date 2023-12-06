package sprint1_0.product.service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;

import javafx.concurrent.Task;
import sprint1_0.product.model.Position;
import sprint1_0.product.model.PositionCircle;

public class ComputerGamePlayService {

  public void makeComputerFill(List<Position> positionCircleList, ExecutorService executorService) {

    Task<Void> computerMoveTask =
        new Task<Void>() {
          @Override
          protected Void call() throws Exception {
            Random random = new Random();
            int randomIndex = random.nextInt(positionCircleList.size());
            PositionCircle c = positionCircleList.get(randomIndex).getPositionCircle();
            ComputerAutomaticService computerAutomaticService = new ComputerAutomaticService();
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
            computerAutomaticService.clickOn(c);

            return null;
          }
        };

    executorService.submit(computerMoveTask);
  }
}
