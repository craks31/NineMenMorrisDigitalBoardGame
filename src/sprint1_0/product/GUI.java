package sprint1_0.product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application {

 private List<PositionCircle> positionCircleList;
 
 private List<Circle> player1Coins;
 
 private List<Circle> player2Coins;
 
 

public List<Circle> getPlayer1Coins() {
return player1Coins;}

public void setPlayer1Coins(List<Circle> player1Coins) {
this.player1Coins = player1Coins;}

public List<Circle> getPlayer2Coins() {
return player2Coins;}

public void setPlayer2Coins(List<Circle> player2Coins) {
this.player2Coins = player2Coins;}

public GUI() {
    super();
  }

@Override
  public void start(Stage primaryStage) throws Exception {
    try {

      // Setting title to Window Pop-up.
      primaryStage.setTitle("Nine Men Morris Digital Board Game");
      // scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Scene scene = new Scene(boardInit(),700,600);
	      //setting color to the scene 
		scene.setFill(Color.ROSYBROWN); 


       
      primaryStage.setScene(scene);
      primaryStage.show();
      
      EventHandler<javafx.scene.input.MouseEvent> eventHandler = 
    		   new EventHandler<javafx.scene.input.MouseEvent>() { 
    		   
    		   @Override 
    		   public void handle(javafx.scene.input.MouseEvent e) { 
    			   
    		      getPlayer1Coins().get(0).setFill(Color.DARKSLATEBLUE);             
    		   } 
    		};    

    		for(int i=0; i<getPlayer1Coins().size();i++) {
    			//Adding the event handler 
    			getPlayer1Coins().get(i).addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
    		}
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  private Group boardInit() throws FileNotFoundException {
	  
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

    //outermost circle
    PositionCircle o1c1r1 = new PositionCircle(175.0d,175.0d,16.0d, Color.ROSYBROWN);
    PositionCircle o1c1r2 = new PositionCircle(175.0d,175.0d*2,16.0d, Color.ROSYBROWN);
    PositionCircle o1c1r3 = new PositionCircle(175.0d,175.0d*3,16.0d, Color.ROSYBROWN);
    PositionCircle o1c2r1 = new PositionCircle(175.0d*2,175.0d,16.0d, Color.ROSYBROWN);
    PositionCircle o1c2r3 = new PositionCircle(175.0d*2,175.0d*3,16.0d, Color.ROSYBROWN);
    PositionCircle o1c3r1 = new PositionCircle(175.0d*3,175.0d,16.0d, Color.ROSYBROWN);
    PositionCircle o1c3r2 = new PositionCircle(175.0d*3,175.0d*2,16.0d, Color.ROSYBROWN);
    PositionCircle o1c3r3 = new PositionCircle(175.0d*3,175.0d*3,16.0d, Color.ROSYBROWN);
    
    positionCircleList.add(o1c1r1);
    positionCircleList.add(o1c1r2);
    positionCircleList.add(o1c1r3);
    positionCircleList.add(o1c2r1);
    positionCircleList.add(o1c2r3);
    positionCircleList.add(o1c3r1);
    positionCircleList.add(o1c3r2);
    positionCircleList.add(o1c3r3);
    
    
    PositionCircle o2c1r1 = new PositionCircle(235.0d,235.0d,16.0d, Color.ROSYBROWN);
    PositionCircle o2c1r2 = new PositionCircle(235.0d,350.0d,16.0d, Color.ROSYBROWN);
    PositionCircle o2c1r3 = new PositionCircle(235.0d,465.0d,16.0d, Color.ROSYBROWN);
    PositionCircle o2c2r1 = new PositionCircle(350.0d,235.0d,16.0d, Color.ROSYBROWN);
    PositionCircle o2c2r3 = new PositionCircle(350.0d,465.0d,16.0d, Color.ROSYBROWN);
    PositionCircle o2c3r1 = new PositionCircle(465.0d,235.0d,16.0d, Color.ROSYBROWN);
    PositionCircle o2c3r2 = new PositionCircle(465.0d,350.0d,16.0d, Color.ROSYBROWN);
    PositionCircle o2c3r3 = new PositionCircle(465.0d,465.0d,16.0d, Color.ROSYBROWN);
    
    positionCircleList.add(o2c1r1);
    positionCircleList.add(o2c1r2);
    positionCircleList.add(o2c1r3);
    positionCircleList.add(o2c2r1);
    positionCircleList.add(o2c2r3);
    positionCircleList.add(o2c3r1);
    positionCircleList.add(o2c3r2);
    positionCircleList.add(o2c3r3);
   
    PositionCircle o3c1r1 = new PositionCircle(295.0d,295.0d,16.0d, Color.ROSYBROWN);
    PositionCircle o3c1r2 = new PositionCircle(295.0d,350.0d,16.0d, Color.ROSYBROWN);
    PositionCircle o3c1r3 = new PositionCircle(295.0d,405.0d,16.0d, Color.ROSYBROWN);
    PositionCircle o3c2r1 = new PositionCircle(350.0d,295.0d,16.0d, Color.ROSYBROWN);
    PositionCircle o3c2r3 = new PositionCircle(350.0d,405.0d,16.0d, Color.ROSYBROWN);
    PositionCircle o3c3r1 = new PositionCircle(405.0d,295.0d,16.0d, Color.ROSYBROWN);
    PositionCircle o3c3r2 = new PositionCircle(405.0d,350.0d,16.0d, Color.ROSYBROWN);
    PositionCircle o3c3r3 = new PositionCircle(405.0d,405.0d,16.0d, Color.ROSYBROWN);
    
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
    
		Group board = new Group(text1, text2, imageView);
		positionCircleList.forEach(board.getChildren()::add);
	    for(int i=0;i<9;i++) {
	    	Circle c1;
	    	Circle c2;
	    	if(i<5) {
	    		c1 = new Circle(25.0d+(i*25),200.0d,10.0d, Color.CORNFLOWERBLUE);
	    		c2 = new Circle(25.0d+(i*25),400.0d,10.0d, Color.GREEN);
	    	}
	    	else {
	    		c1 = new Circle(25.0d+((i-5)*25),225.0d,10.0d, Color.CORNFLOWERBLUE);
	    		c2 = new Circle(25.0d+((i-5)*25),425.0d,10.0d, Color.GREEN);
	    		}
	    	player1Coins.add(c1);
	    	player2Coins.add(c2);

	    }	
	    player1Coins.forEach(board.getChildren()::add);
	    player2Coins.forEach(board.getChildren()::add);
	    setPositionCircleList(positionCircleList);
	    setPlayer1Coins(player1Coins);
	    setPlayer2Coins(player2Coins);
		return board;
	}

  public List<PositionCircle> getPositionCircleList() {
    return positionCircleList;
  }

  public void setPositionCircleList(List<PositionCircle> positionCircleList) {
    this.positionCircleList = positionCircleList;
  }
  
}
