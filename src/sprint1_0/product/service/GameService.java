/**package sprint1_0.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.StrokeTransition;
import javafx.event.EventHandler;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import sprint1_0.product.helper.DisplayUtil;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;
import sprint1_0.product.model.PositionCircle;

public class GameService {
  DisplayUtil displayUtil = new DisplayUtil();
  CoinPlacementService coinPlacementService = new CoinPlacementService();
  CoinMovementService coinMovementService = new CoinMovementService();
  CoinRemovalService coinRemovalService = new CoinRemovalService();
  /**
   * This method is used to deicde a player turn randomly.
   *
   * @param board
   * @return
 
  public Color decidePlayerColorTurn(Board board) {
    Random rand = new Random();
    boolean isPlayer1 = rand.nextBoolean();
    if (isPlayer1) {
      return board.getPlayer1().getPlayerColor();
    } else {
      return board.getPlayer2().getPlayerColor();
    }
  }

  public void initDisplayAndStartGame(Board board, Color decidedColor) {

    EventHandler<javafx.scene.input.MouseEvent> decideButtonEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {

          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            displayUtil.displayPlayerTurn(board, decidedColor);
            board.getDecideButton().setDisable(true);
            placeCoinsSetup(board);
          }
        };

    // Adding the event handler
    board.getDecideButton().setOnMouseClicked(decideButtonEventHandler);
  }

  public void startGame(Board board, Color decideColor) {
    initDisplayAndStartGame(board, decideColor);
  }

  public void placeCoinsSetup(Board board) {
    board.setOp("FILL");
    for (int i = 0; i < board.getBlankPositionList().size(); i++) {
      PositionCircle positionCircle = board.getBlankPositionList().get(i).getPositionCircle();
      // Adding the event handler
      positionCircle.setDisable(false);
      positionCircle.addEventHandler(MouseEvent.MOUSE_CLICKED, coinMouseEventHandler(board));
      positionCircle.setUserData(i);
    }
  }

  private EventHandler<MouseEvent> coinMouseEventHandler(Board board) {
     
    EventHandler<MouseEvent> coinMouseEventHandler =		
        new EventHandler<javafx.scene.input.MouseEvent>() {

          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            Circle clickedCircle = (Circle) e.getSource();
            StrokeTransition strokeTransition = new StrokeTransition(Duration.seconds(10.5));
            strokeTransition.setFromValue(Color.BLUE); // Initial outer color
            strokeTransition.setToValue(Color.RED);     // Blinking outer color
            strokeTransition.setCycleCount(Animation.INDEFINITE);
            if (board.getOp().equals("FILL")) {
              coinPlacementService.coinFillEvent(board, clickedCircle);
              if (checkMill(
                  board,
                  board.getAllPositionList().get((int) clickedCircle.getUserData()),
                  clickedCircle.getFill())) {
                coinRemovalService.prepareForCoinRemoval(board, clickedCircle.getFill());
              }
              clickedCircle.setDisable(true);
            } else if (board.getOp().equals("MOVE")) {
              coinMovementService.coinMoveEvent(board, clickedCircle, strokeTransition);
              board.setPhase2Started(true);
              clickedCircle.setDisable(true);
            } else if (board.getOp().equals("REMOVE")) {
              coinRemovalService.coinRemoveEvent(board, clickedCircle, strokeTransition);
              clickedCircle.setDisable(false);
            }
            else if (board.getOp().equals("FLY")) {
               // coinRemovalService.coinRemoveEvent(board, clickedCircle, strokeTransition);
                //clickedCircle.setDisable(false);
            	//decide game
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
    List<Position> horizontalMillList = new ArrayList<>();
    List<Position> verticalMillList = new ArrayList<>();

    if (position.getLeft() != null
        && position.getLeft().isFilled()
        && position.getLeft().getFill().equals(color)) {
      horizontalMillDecider++;
      leftDecider = true;
      horizontalMillList.add(position.getLeft()); // Add the left position to the list

    }
    if (position.getUp() != null
        && position.getUp().isFilled()
        && position.getUp().getFill().equals(color)) {
      verticalMillDecider++;
      upDecider = true;
      verticalMillList.add(position.getLeft());
    }
    if (position.getRight() != null
        && position.getRight().isFilled()
        && position.getRight().getFill().equals(color)) {
      horizontalMillDecider++;
      rightDecider = true;
      horizontalMillList.add(position.getRight());
    }
    if (position.getDown() != null
        && position.getDown().isFilled()
        && position.getDown().getFill().equals(color)) {
      verticalMillDecider++;
      downDecider = true;
      verticalMillList.add(position.getDown()); // Add the down position to the list

    }

    if (horizontalMillDecider == 1) {
      if (leftDecider) {
        if (position.getLeft().getLeft() != null
            && position.getLeft().getLeft().isFilled()
            && position.getLeft().getLeft().getFill().equals(color)) {
          horizontalMillDecider++;
        }
      } else if (rightDecider) {
        if (position.getRight().getRight() != null
            && position.getRight().getRight().isFilled()
            && position.getRight().getRight().getFill().equals(color)) {
          horizontalMillDecider++;
        }
      }

    } else if (verticalMillDecider == 1) {
      if (upDecider) {
        if (position.getUp().getUp() != null
            && position.getUp().getUp().isFilled()
            && position.getUp().getUp().getFill().equals(color)) {
          verticalMillDecider++;
        }
      } else if (downDecider) {
        if (position.getDown().getDown() != null
            && position.getDown().getDown().isFilled()
            && position.getDown().getDown().getFill().equals(color)) {
          verticalMillDecider++;
        }
      }
    }
    if (horizontalMillDecider == 2 || verticalMillDecider == 2) {
      millFormed = true;
      
    System.out.println("Hor" + horizontalMillDecider);
    System.out.println("Ver" + verticalMillDecider);
    return millFormed;
    
    }
	return millFormed;
  

//Timeline blinkTimeline = new Timeline();


for (int i = 0; i < horizontalMillList.size(); i++)
//{    List<Position> horizontalMillList;
	Circle circle = horizontalMillList.get(i);

    KeyFrame keyFrame = new KeyFrame(Duration.millis(500), e -> {
        if (circle.getBlendMode() == BlendMode.SRC_OVER) {
            circle.setBlendMode(BlendMode.COLOR_BURN);
        } else {
            circle.setBlendMode(BlendMode.SRC_OVER);
        }
    ;
    
    blinkTimeline.getKeyFrames().add(keyFrame);
    
//blinkTimeline.setCycleCount(Animation.INDEFINITE);
//blinkTimeline.play();

 }

    
*/



/** package sprint1_0.product.service;

import java.util.Random;
import javafx.animation.Animation;
import java.util.HashSet; // Added import for HashSet
import java.util.Set; // Added import for Set
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent; // Correct import for MouseEvent
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import sprint1_0.product.helper.DisplayUtil;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;
import sprint1_0.product.model.PositionCircle;

public class GameService {
  DisplayUtil displayUtil = new DisplayUtil();
  CoinPlacementService coinPlacementService = new CoinPlacementService();
  CoinMovementService coinMovementService = new CoinMovementService();
  CoinRemovalService coinRemovalService = new CoinRemovalService();
  
  // Add a Timeline for the blinking effect
  private Timeline blinkTimeline = new Timeline();

  // Create a set to store all mill-formed circles
  private Set<Circle> millFormedCircles = new HashSet<>();

  /**
   * This method is used to decide a player's turn randomly.
   *
   * @param board
   * @return
   
  public Color decidePlayerColorTurn(Board board) {
    Random rand = new Random();
    boolean isPlayer1 = rand.nextBoolean();
    if (isPlayer1) {
      return board.getPlayer1().getPlayerColor();
    } else {
      return board.getPlayer2().getPlayerColor();
    }
  }

  public void initDisplayAndStartGame(Board board, Color decidedColor) {
	    EventHandler<MouseEvent> decideButtonEventHandler = // Correct import for MouseEvent
        new EventHandler<javafx.scene.input.MouseEvent>() {

          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            displayUtil.displayPlayerTurn(board, decidedColor);
            board.getDecideButton().setDisable(true);
            placeCoinsSetup(board);
          }
        };

    // Adding the event handler
    board.getDecideButton().setOnMouseClicked(decideButtonEventHandler);
  }

  public void startGame(Board board, Color decideColor) {
    initDisplayAndStartGame(board, decideColor);
  }

  public void placeCoinsSetup(Board board) {
    board.setOp("FILL");
    for (int i = 0; i < board.getBlankPositionList().size(); i++) {
      PositionCircle positionCircle = board.getBlankPositionList().get(i).getPositionCircle();
      // Adding the event handler
      positionCircle.setDisable(false);
      positionCircle.addEventHandler(MouseEvent.MOUSE_CLICKED, coinMouseEventHandler(board));
      positionCircle.setUserData(i);
    }
  }

  private EventHandler<MouseEvent> coinMouseEventHandler(Board board) {
    EventHandler<MouseEvent> coinMouseEventHandler =
        new EventHandler<javafx.scene.input.MouseEvent>() {

          @Override
          public void handle(javafx.scene.input.MouseEvent e) {
            Circle clickedCircle = (Circle) e.getSource;
            if (board.getOp().equals("FILL")) {
              coinPlacementService.coinFillEvent(board, clickedCircle);
              if (checkMill(
                  board,
                  board.getAllPositionList().get((int) clickedCircle.getUserData()),
                  clickedCircle.getFill())) {
                millFormedCircles.add(clickedCircle); // Add the circle to mill-formed set
                blinkMillCircles(); // Blink all circles in the mill
                coinRemovalService.prepareForCoinRemoval(board, clickedCircle.getFill());
              }
              clickedCircle.setDisable(true);
            } else if (board.getOp().equals("MOVE")) {
              coinMovementService.coinMoveEvent(board, clickedCircle, null);
              board.setPhase2Started(true);
              clickedCircle.setDisable(true);
            } else if (board.getOp().equals("REMOVE")) {
              coinRemovalService.coinRemoveEvent(board, clickedCircle, null);
              stopBlinkingMillCircles(); // Stop the blinking effect
              clickedCircle.setDisable(false);
            } else if (board.getOp().equals("FLY")) {
            	//coinRemovalService.coinRemoveEvent(board, clickedCircle, strokeTransition);
            	//clickedCircle.setDisable(false);
            	// Decide the game
            }
          }
        };
    return coinMouseEventHandler;
  }

  private boolean checkMill(Board board, Position position, Paint color) {
    // ... Your existing code to check for mill formation
	 
		    boolean millFormed = false;
		    int horizontalMillDecider = 0;
		    int verticalMillDecider = 0;
		    boolean leftDecider = false;
		    boolean rightDecider = false;
		    boolean upDecider = false;
		    boolean downDecider = false;
		    if (position.getLeft() != null
		        && position.getLeft().isFilled()
		        && position.getLeft().getFill().equals(color)) {
		      horizontalMillDecider++;
		      
		      leftDecider = true;
		    }
		    if (position.getUp() != null
		        && position.getUp().isFilled()
		        && position.getUp().getFill().equals(color)) {
		      verticalMillDecider++;
		      upDecider = true;
		    }
		    if (position.getRight() != null
		        && position.getRight().isFilled()
		        && position.getRight().getFill().equals(color)) {
		      horizontalMillDecider++;
		      rightDecider = true;
		    }
		    if (position.getDown() != null
		        && position.getDown().isFilled()
		        && position.getDown().getFill().equals(color)) {
		      verticalMillDecider++;
		      downDecider = true;
		    }

		    if (horizontalMillDecider == 1) {
		      if (leftDecider) {
		        if (position.getLeft().getLeft() != null
		            && position.getLeft().getLeft().isFilled()
		            && position.getLeft().getLeft().getFill().equals(color)) {
		          horizontalMillDecider++;
		        }
		      } else if (rightDecider) {
		        if (position.getRight().getRight() != null
		            && position.getRight().getRight().isFilled()
		            && position.getRight().getRight().getFill().equals(color)) {
		          horizontalMillDecider++;
		        }
		      }

		    } else if (verticalMillDecider == 1) {
		      if (upDecider) {
		        if (position.getUp().getUp() != null
		            && position.getUp().getUp().isFilled()
		            && position.getUp().getUp().getFill().equals(color)) {
		          verticalMillDecider++;
		        }
		      } else if (downDecider) {
		        if (position.getDown().getDown() != null
		            && position.getDown().getDown().isFilled()
		            && position.getDown().getDown().getFill().equals(color)) {
		          verticalMillDecider++;
		        }
		      }
		    }
		    if (horizontalMillDecider == 2 || verticalMillDecider == 2) {
		      millFormed = true;
		    }
		    System.out.println("Hor" + horizontalMillDecider);
		    System.out.println("Ver" + verticalMillDecider);
		    return millFormed;
		  }


  // Start blinking all mill circles
  private void blinkMillCircles() {
    // Stop any existing blinking
    stopBlinkingMillCircles();

    // Create a blinking effect for each circle in the mill and add them to the timeline
    for (Circle circle : millFormedCircles) {
      KeyFrame keyFrame = new KeyFrame(Duration.millis(500), e -> {
        if (circle.getBlendMode() == BlendMode.SRC_OVER) {
          circle.setBlendMode(BlendMode.COLOR_BURN);
        } else {
          circle.setBlendMode(BlendMode.SRC_OVER);
        }
      });
      blinkTimeline.getKeyFrames().add(keyFrame);
    }

    blinkTimeline.setCycleCount(Animation.INDEFINITE);
    blinkTimeline.play();
  }

  // Stop the blinking effect for all mill circles
  private void stopBlinkingMillCircles() {
    blinkTimeline.stop();
    for (Circle circle : millFormedCircles) {
      circle.setBlendMode(BlendMode.SRC_OVER);
    }
  }
}*/

package sprint1_0.product.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.StrokeTransition;
import javafx.event.EventHandler;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import sprint1_0.product.helper.DisplayUtil;
import sprint1_0.product.model.Board;
import sprint1_0.product.model.Position;
import sprint1_0.product.model.PositionCircle;

public class GameService {
    DisplayUtil displayUtil = new DisplayUtil();
    CoinPlacementService coinPlacementService = new CoinPlacementService();
    CoinMovementService coinMovementService = new CoinMovementService();
    CoinRemovalService coinRemovalService = new CoinRemovalService();
//	private Circle[] horizontalMillList;
    
//    private Timeline blinkTimeline = new Timeline();
//	private Position[] horizontalMillList;
//	private Position[] verticalMillList;
    
    public Color decidePlayerColorTurn(Board board) {
        Random rand = new Random();
        boolean isPlayer1 = rand.nextBoolean();
        if (isPlayer1) {
            return board.getPlayer1().getPlayerColor();
        } else {
            return board.getPlayer2().getPlayerColor();
        }
    }

    public void initDisplayAndStartGame(Board board, Color decidedColor) {
        EventHandler<javafx.scene.input.MouseEvent> decideButtonEventHandler =
            new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent e) {
                    displayUtil.displayPlayerTurn(board, decidedColor);
                    board.getDecideButton().setDisable(true);
                    placeCoinsSetup(board);
                }
            };

        // Adding the event handler
        board.getDecideButton().setOnMouseClicked(decideButtonEventHandler);
    }
 
    public void startGame(Board board, Color decideColor) {
        initDisplayAndStartGame(board, decideColor);
    }

    public void placeCoinsSetup(Board board) {
        board.setOp("FILL");
        for (int i = 0; i < board.getBlankPositionList().size(); i++) {
            PositionCircle positionCircle = board.getBlankPositionList().get(i).getPositionCircle();
            // Adding the event handler
            positionCircle.setDisable(false);
            positionCircle.addEventHandler(MouseEvent.MOUSE_CLICKED, coinMouseEventHandler(board));
            positionCircle.setUserData(i);
        }
    }

    private EventHandler<MouseEvent> coinMouseEventHandler(Board board) {
        EventHandler<MouseEvent> coinMouseEventHandler =		
            new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent e) {
                    Circle clickedCircle = (Circle) e.getSource();
                    StrokeTransition strokeTransition = new StrokeTransition(Duration.seconds(10.5));
                    strokeTransition.setFromValue(Color.BLUE); // Initial outer color
                    strokeTransition.setToValue(Color.RED);     // Blinking outer color
                    strokeTransition.setCycleCount(Animation.INDEFINITE);
                    if (board.getOp().equals("FILL")) {
                        coinPlacementService.coinFillEvent(board, clickedCircle);
                        if (checkMill(
                            board,
                            board.getAllPositionList().get((int) clickedCircle.getUserData()),
                            clickedCircle.getFill())) {
                            coinRemovalService.prepareForCoinRemoval(board, clickedCircle.getFill());
                            blinkMillCircles(board, board.getAllPositionList().get((int) clickedCircle.getUserData()));
                        }
                        clickedCircle.setDisable(true);
                    } else if (board.getOp().equals("MOVE")) {
                        coinMovementService.coinMoveEvent(board, clickedCircle, strokeTransition);
                        board.setPhase2Started(true);
                        clickedCircle.setDisable(true);
                    } else if (board.getOp().equals("REMOVE")) {
                        coinRemovalService.coinRemoveEvent(board, clickedCircle, strokeTransition);
                        clickedCircle.setDisable(false);
                    } else if (board.getOp().equals("FLY")) {
                        // coinRemovalService.coinRemoveEvent(board, clickedCircle, strokeTransition);
                        // clickedCircle.setDisable(false);
                        // decide game
                    }
                }

				private void blinkMillCircles(Board board, Position position) {
					// TODO Auto-generated method stub
					
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
        List<Position> horizontalMillList = new ArrayList<>();
        List<Position> verticalMillList = new ArrayList<>();

        if (position.getLeft() != null
            && position.getLeft().isFilled()
            && position.getLeft().getFill().equals(color)) {
            horizontalMillDecider++;
            leftDecider = true;
            horizontalMillList.add(position.getLeft()); // Add the left position to the list
        }
        if (position.getUp() != null
            && position.getUp().isFilled()
            && position.getUp().getFill().equals(color)) {
            verticalMillDecider++;
            upDecider = true;
            verticalMillList.add(position.getUp());
        }
        if (position.getRight() != null
            && position.getRight().isFilled()
            && position.getRight().getFill().equals(color)) {
            horizontalMillDecider++;
            rightDecider = true;
            horizontalMillList.add(position.getRight());
        }
        if (position.getDown() != null
            && position.getDown().isFilled()
            && position.getDown().getFill().equals(color)) {
            verticalMillDecider++;
            downDecider = true;
            verticalMillList.add(position.getDown()); // Add the down position to the list
        }

        if (horizontalMillDecider == 1) {
            if (leftDecider) {
                if (position.getLeft().getLeft() != null
                    && position.getLeft().getLeft().isFilled()
                    && position.getLeft().getLeft().getFill().equals(color)) {
                    horizontalMillDecider++;
                    horizontalMillList.add(position.getLeft().getLeft());
                }
            } else if (rightDecider) {
                if (position.getRight().getRight() != null
                    && position.getRight().getRight().isFilled()
                    && position.getRight().getRight().getFill().equals(color)) {
                    horizontalMillDecider++;
                    horizontalMillList.add(position.getRight().getRight());
                }
            }
        } else if (verticalMillDecider == 1) {
            if (upDecider) {
                if (position.getUp().getUp() != null
                    && position.getUp().getUp().isFilled()
                    && position.getUp().getUp().getFill().equals(color)) {
                    verticalMillDecider++;
                    verticalMillList.add(position.getUp().getUp());
                }
            } else if (downDecider) {
                if (position.getDown().getDown() != null
                    && position.getDown().getDown().isFilled()
                    && position.getDown().getDown().getFill().equals(color)) {
                    verticalMillDecider++;
                    verticalMillList.add(position.getDown().getDown());
                }
            }
        }
        if (horizontalMillDecider == 2 || verticalMillDecider == 2) {
            millFormed = true;
            System.out.println("Hor" + horizontalMillDecider);
            System.out.println("Ver" + verticalMillDecider);
            blinkMillCircles1(horizontalMillList, verticalMillList);
        
//        return millFormed;
        
        
        }
		return millFormed;}

  
 private static Map<Circle, Timeline> blinkTimelines = new HashMap<>();

    private void blinkMillCircles1(List<Position> horizontalMillList, List<Position> verticalMillList) {
        for (Position position : horizontalMillList) {
            startBlinking(position.getPositionCircle());
        }
        for (Position position : verticalMillList) {
            startBlinking(position.getPositionCircle());
        }
    }

private void startBlinking(Circle circle) {
    if (!blinkTimelines.containsKey(circle)) {
        Timeline blinkTimeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500), e -> {
            if (circle.getBlendMode() == BlendMode.SRC_OVER) {
                circle.setBlendMode(BlendMode.EXCLUSION);
            } else {
                circle.setBlendMode(BlendMode.SRC_OVER);
            }
        });
        blinkTimeline.getKeyFrames().add(keyFrame);
        blinkTimeline.setCycleCount(Animation.INDEFINITE);
        blinkTimeline.play();
        blinkTimelines.put(circle, blinkTimeline);
    }
}
/*public void stopBlinking(Circle circle) {
    if (blinkTimelines.containsKey(circle)) {
        Timeline blinkTimeline = blinkTimelines.get(circle);
        blinkTimeline.stop();
        circle.setBlendMode(BlendMode.SRC_OVER);
        blinkTimelines.remove(circle);
    }
}*/

public void stopBlinking(Circle circle) {
    if (blinkTimelines.containsKey(circle)) {
        Timeline blinkTimeline = blinkTimelines.get(circle);
        blinkTimeline.stop();
        circle.setBlendMode(BlendMode.SRC_OVER);
        blinkTimelines.remove(circle);
    }
}

public void stopBlinking(List<Position> positions) {
    for (Position position : positions) {
        stopBlinking(position.getPositionCircle());
    }
}
}


