package sprint1_0.product.service;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sprint1_0.product.constants.GameConstants;
import sprint1_0.product.helper.DisplayUtil;
import sprint1_0.product.helper.PositionHelper;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;

public class CoinRemovalService {
  DisplayUtil displayUtil = new DisplayUtil();
  CoinFlyService coinFlyService = new CoinFlyService();
  CoinMovementService coinMoveService = new CoinMovementService();
  GameEndService gameEndService = new GameEndService();
  CoinMillService coinMillService = new CoinMillService();
  ComputerGamePlayService compGamePlayService = new ComputerGamePlayService();
  CoinMovementService coinMovementService = new CoinMovementService();

  public void coinRemoveEvent(Board board, Circle clickedCircle, ExecutorService executorService, Stage stage) {
    System.out.println("REMOVE TRIGGERED");
    clickedCircle.setFill(GameConstants.BACKGROUNDCOLOR);
    Position clickedPosition = board.getAllPositionList().get((int) clickedCircle.getUserData());
    clickedPosition.setFilled(false);
    coinMillService.breakTheMill(clickedPosition);
    if (!board.isPhase2Started()) {
      board.getAllPositionList().stream()
          .filter(pos -> pos.isFilled())
          .forEach(e -> e.getPositionCircle().setDisable(true));
      board.getAllPositionList().stream()
          .filter(pos -> !pos.isFilled())
          .forEach(e -> e.getPositionCircle().setDisable(false));
      if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER1COLOR)) {
        System.out.println("PLAYER 1 TURN");
        PositionHelper.gameHistoryPrinterForPlayer2(clickedPosition, board.getOp());
        displayUtil.displayPlayerTurn(board, GameConstants.PLAYER2COLOR);
        // Computer turn after Computer Coin removal by Human
        if (board.isPlayer2Computer()) {
          board.setOp("FILL");
          board.getBlankPositionList().add(clickedPosition);
          System.out.println("DEBUG -- Computer Fill After Human Removing A Computer Coin In Phase1 " ); 
          compGamePlayService.makeComputerFill(board.getBlankPositionList(), executorService);
        }
      } else if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER2COLOR)) {
        System.out.println("PLAYER 2 TURN");
        PositionHelper.gameHistoryPrinterForPlayer1(clickedPosition, board.getOp());
        displayUtil.displayPlayerTurn(board, GameConstants.PLAYER1COLOR);
      }
      board.setOp("FILL");
      System.out.println("-------Board is set for Filling-------");
    } else if (board.isPhase2Started()) {
      if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER1COLOR)) {
        System.out.println("PLAYER 1 TURN");
        PositionHelper.gameHistoryPrinterForPlayer2(clickedPosition, board.getOp());
        PositionHelper.disableFilledPositions(board.getPlayer1().getFilledPositions());
        PositionHelper.enableFilledPositions(board.getPlayer2().getFilledPositions());
        PositionHelper.setPlayerPositionsList(board);
        System.out.println(
            "Player 1 left out coins - " + board.getPlayer1().getFilledPositions().size());
        System.out.println(
            "Player 2 left out coins - " + board.getPlayer2().getFilledPositions().size());

        if (board.getPlayer2().getFilledPositions().size() == 3) {
          coinFlyService.prepareForCoinFlyMovement(board, GameConstants.PLAYER2COLOR);
          displayUtil.displayPlayerTurn(board, GameConstants.PLAYER2COLOR);
          if (board.isPlayer2Computer()
              && board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER2COLOR)) {
            board.getBlankPositionList().add(clickedPosition);
            System.out.println("DEBUG -- Computer Fly After Human Removing A Computer Coin In Phase3 " ); 
            compGamePlayService.makeComputerFill(
                PositionHelper.getPlayer2FilledCoins(board.getAllPositionList()), executorService);
          }
        }
        if (gameEndService.decideGameEndCondition(board)) {
          System.out.println("END TRIGGERED");
          gameEndService.prepareForGameEnd(board, stage);
        }
        if (board.getOp().equals("REMOVE")) {
          board.setOp("MOVE");
          clickedCircle.setDisable(true);
          clickedPosition.getPositionCircle().setDisable(true);
          displayUtil.displayPlayerTurn(board, GameConstants.PLAYER2COLOR);
          board.getBlankPositionList().add(clickedPosition);
          coinMoveService.prepareForCoinMovement(board, executorService);
          // Computer turn after Computer Coin removal by Human
          if (board.isPlayer2Computer()) {
            board.getBlankPositionList().add(clickedPosition);
            System.out.println("DEBUG -- Computer Move After Human Removing A Computer Coin In Phase2 " ); 
            compGamePlayService.makeComputerFill(
                    coinMovementService.findComputerCoinsWithEmptyNeighbors(
                        board, PositionHelper.getPlayer2FilledCoins(board.getAllPositionList())),
                    executorService);
            
          }
        }

      } else if (board.getDisplayCircleTurn().getFill().equals(GameConstants.PLAYER2COLOR)) {
        System.out.println("PLAYER 2 TURN");
        PositionHelper.gameHistoryPrinterForPlayer1(clickedPosition, board.getOp());
        PositionHelper.disableFilledPositions(board.getPlayer2().getFilledPositions());
        PositionHelper.enableFilledPositions(board.getPlayer1().getFilledPositions());
        PositionHelper.setPlayerPositionsList(board);
        System.out.println(
            "Player 1 left out coins - " + board.getPlayer1().getFilledPositions().size());
        System.out.println(
            "Player 2 left out coins - " + board.getPlayer2().getFilledPositions().size());

        if (board.getPlayer1().getFilledPositions().size() == 3) {
          coinFlyService.prepareForCoinFlyMovement(board, GameConstants.PLAYER1COLOR);
          displayUtil.displayPlayerTurn(board, GameConstants.PLAYER1COLOR);
        }
        if (gameEndService.decideGameEndCondition(board)) {
          System.out.println("END TRIGGERED");
          gameEndService.prepareForGameEnd(board, stage);
        }
        if (board.getOp().equals("REMOVE")) {
          clickedCircle.setDisable(true);
          clickedPosition.getPositionCircle().setDisable(true);
          board.setOp("MOVE");
          board.getBlankPositionList().add(clickedPosition);
          displayUtil.displayPlayerTurn(board, GameConstants.PLAYER1COLOR);
          coinMoveService.prepareForCoinMovement(board, executorService);
        }
      }
    }

    board.getBlankPositionList().add(clickedPosition);
  }

  public void prepareForCoinRemoval(Board board, Paint color, ExecutorService executorService) {
    List<Position> player1FilledPositions =
        PositionHelper.getPlayer1FilledCoins(board.getAllPositionList());

    List<Position> player2FilledPositions =
        PositionHelper.getPlayer2FilledCoins(board.getAllPositionList());

    PositionHelper.disableUnFilledPositions(board.getAllPositionList());

    board.getPlayer2().setFilledPositions(player2FilledPositions);
    board.getPlayer1().setFilledPositions(player1FilledPositions);

    // logic
    if (color.equals(GameConstants.PLAYER1COLOR)) {
      displayUtil.displayPlayerTurn(board, GameConstants.PLAYER1COLOR);
      System.out.println("Checking Non Mill Player 2 coin");
      if (!coinMillService.enableNonMillOpponentCoinsToRemove(board, player2FilledPositions)) {
        System.out.println("No Non Mill, enabling all Player 2 Coins for removal");
        PositionHelper.enablePositions(player2FilledPositions);
      }
      board.setOp("REMOVE");
      System.out.println("-------Board is set for Removal-------");
    } else {
      displayUtil.displayPlayerTurn(board, GameConstants.PLAYER2COLOR);
      System.out.println("Checking Non Mill Player 1 coin");
      if (!coinMillService.enableNonMillOpponentCoinsToRemove(board, player1FilledPositions)) {
        System.out.println("No Non Mill, enabling all Player 1 Coins for removal");
        PositionHelper.enablePositions(player1FilledPositions);
        board.setOp("REMOVE");
        System.out.println("-------Board is set for Removal-------");
      }
      if (board.isPlayer2Computer() && board.getOp().equals("REMOVE") ) {
    	  System.out.println("DEBUG -- Initiating Human Coin REMOVAL by Computer After Computer forms a mill ");
        if (board.getNonMillCoinsForComputer().isEmpty()) {
          System.out.println("Possible Human Coins for removal: " + player1FilledPositions.size());
          compGamePlayService.makeComputerFill(player1FilledPositions, executorService);
        } else {
          System.out.println(
              "Possible Human Coins for removal: " + board.getNonMillCoinsForComputer().size());
          compGamePlayService.makeComputerFill(board.getNonMillCoinsForComputer(), executorService);
        }
      }
    }
  }
}
