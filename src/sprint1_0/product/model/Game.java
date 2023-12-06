package sprint1_0.product.model;

import java.util.Queue;

public class Game {

  private boolean replayEnabled;

  private Queue<RecordedMove> moveQueue;

  public boolean isReplayEnabled() {
    return replayEnabled;
  }

  public void setReplayEnabled(boolean replayEnabled) {
    this.replayEnabled = replayEnabled;
  }

  public Queue<RecordedMove> getMoveQueue() {
    return moveQueue;
  }

  public void setMoveQueue(Queue<RecordedMove> moveQueue) {
    this.moveQueue = moveQueue;
  }
}
