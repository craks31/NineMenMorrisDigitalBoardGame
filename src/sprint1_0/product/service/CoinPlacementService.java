package sprint1_0.product.service;

import java.util.Random;

import javafx.scene.paint.Color;
import sprint1_0.product.model.Board;

public class CoinPlacementService {
	
public Color decidePlayerColorTurn(Board board) {
    Random rand = new Random();
    int max = 2;
    int min = 1;
    int num = rand.nextInt((max-min+1)+min);
    if(num==1){
    	return board.getPlayer1().getPlayerColor();
    }
    else {
    	return board.getPlayer2().getPlayerColor();
    }
}


}
