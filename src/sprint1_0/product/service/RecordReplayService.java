package sprint1_0.product.service;

import java.util.LinkedList;
import java.util.Queue;

import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Game;
import sprint1_0.product.model.RecordedMove;
import sprint1_0.product.ui.CommonGameManagerGUI;

public class RecordReplayService {
  private Game game;
  private Queue<RecordedMove> moveQueue;

  public RecordReplayService() {
    this.game = new Game();
    this.moveQueue = new LinkedList<>();
  }

  public void recordMove(Color color, Circle clickedCircle) {
    RecordedMove recordedMove = new RecordedMove();
    recordedMove.setDecidedColor(color);
    recordedMove.setClickedCircle(clickedCircle);
    moveQueue.add(recordedMove);
  }

  public void finishRecording() {
    game.setMoveQueue(moveQueue);
    
  }
  
  public Game getGame() {
    return game;
  }
  
  public void replayGame(Board board) {
	  board.getReplayGameButton().setDisable(false);
	    EventHandler<javafx.scene.input.MouseEvent> replayButtonEventHandler =
	        new EventHandler<javafx.scene.input.MouseEvent>() {
	          @Override
	          public void handle(javafx.scene.input.MouseEvent e) {
	        	  game.setReplayEnabled(true);
	        	  CommonGameManagerGUI gui = new CommonGameManagerGUI();
	        	  gui.getGameInitStage().show();
	        	  board.getReplayGameButton().setDisable(true);
	        }
	    };
	    board.getRecordGameButton().setOnMouseClicked(replayButtonEventHandler);
	  }
}
