package sprint1_0.product.model;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * @author rakesh
 *
 */
public class Board { 
	public Board() {
		super();
	}
	
	private List<Position> blankPositionList;
	
	private List<Position> filledPositionList;
	
	private MasterPlayer player1;
	
	private MasterPlayer player2;
	
	private List<PositionCircle> positionCircleList;

	private Text displayTextTurn;

	private Circle displayCircleTurn;
	
	private Button decideButton;
	
	private Button startNewGameButton;
	
	private Button resetGameButton;

	public List<PositionCircle> getPositionCircleList() {
		return positionCircleList;
	}

	public void setPositionCircleList(List<PositionCircle> positionCircleList) {
		this.positionCircleList = positionCircleList;
	}

	public Text getDisplayTextTurn() {
		return displayTextTurn;
	}

	public void setDisplayTextTurn(Text displayTextTurn) {
		this.displayTextTurn = displayTextTurn;
	}

	public Circle getDisplayCircleTurn() {
		return displayCircleTurn;
	}

	public void setDisplayCircleTurn(Circle displayCircleTurn) {
		this.displayCircleTurn = displayCircleTurn;
	}

	public List<Position> getBlankPositionList() {
		return blankPositionList;
	}

	public void setBlankPositionList(List<Position> blankPositionList) {
		this.blankPositionList = blankPositionList;
	}

	public List<Position> getFilledPositionList() {
		return filledPositionList;
	}

	public void setFilledPositionList(List<Position> filledPositionList) {
		this.filledPositionList = filledPositionList;
	}

	public MasterPlayer getPlayer1() {
		return player1;
	}

	public void setPlayer1(MasterPlayer player1) {
		this.player1 = player1;
	}

	public MasterPlayer getPlayer2() {
		return player2;
	}

	public void setPlayer2(MasterPlayer player2) {
		this.player2 = player2;
	}
	public Button getDecideButton()
	{
		return decideButton;
	}
	public void setDecideButton(Button decideButton)
	{
		this.decideButton=decideButton;
	}
	public Button getStartNewGameButton()
	{
		return startNewGameButton;
	}
	public void setStartNewGameButton(Button startNewGameButton)
	{
		this.startNewGameButton=startNewGameButton;
	}

	public Button getResetGameButton() {
		return resetGameButton;
	}

	public void setResetGameButton(Button resetGameButton) {
		this.resetGameButton = resetGameButton;
	}

	
}
