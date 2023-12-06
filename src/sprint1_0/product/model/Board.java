package sprint1_0.product.model;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import sprint1_0.product.service.RecordReplayService;

/** @author rakesh */
public class Board {
  public Board() {
    super();
  }

  private String op;

  private boolean phase2Started;

  private boolean phase3Started;

  private boolean millCheckByPassed;

  private List<Position> blankPositionList;

  private List<Position> allPositionList;

  private MasterPlayer player1;

  private MasterPlayer player2;

  private List<PositionCircle> positionCircleList;

  private Text displayTextTurn;

  private Circle displayCircleTurn;

  private Button decideButton;

  private Button startNewGameButton;

  private Button resetGameButton;

  private Button replayGameButton;

  private Button recordGameButton;

  private Button frontButton;

  private Button backwardButton;

  private Button forwardButton;

  private Button endButton;

  private boolean isPlayer2Computer;

  private ChoiceDialog<String> choiceDialog;

  private TextInputDialog name1Dialog;

  private TextInputDialog name2Dialog;

  private List<Position> emptyNeighborsForComputer;

  private List<Position> nonMillCoinsForComputer;

  private boolean recordingEnabled;

  private RecordReplayService recordReplayService;
  
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

  public List<Position> getAllPositionList() {
    return allPositionList;
  }

  public void setAllPositionList(List<Position> filledPositionList) {
    this.allPositionList = filledPositionList;
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

  public Button getDecideButton() {
    return decideButton;
  }

  public void setDecideButton(Button decideButton) {
    this.decideButton = decideButton;
  }

  public Button getStartNewGameButton() {
    return startNewGameButton;
  }

  public void setStartNewGameButton(Button startNewGameButton) {
    this.startNewGameButton = startNewGameButton;
  }

  public Button getResetGameButton() {
    return resetGameButton;
  }

  public void setResetGameButton(Button resetGameButton) {
    this.resetGameButton = resetGameButton;
  }

  public Button getFrontButton() {
    return frontButton;
  }

  public void setFrontButton(Button frontButton) {
    this.frontButton = frontButton;
  }

  public Button getBackwardButton() {
    return backwardButton;
  }

  public void setBackwardButton(Button backwardButton) {
    this.backwardButton = backwardButton;
  }

  public Button getForwardButton() {
    return forwardButton;
  }

  public void setForwardButton(Button forwardButton) {
    this.forwardButton = forwardButton;
  }

  public Button getEndButton() {
    return endButton;
  }

  public void setEndButton(Button endButton) {
    this.endButton = endButton;
  }

  public String getOp() {
    return op;
  }

  public void setOp(String op) {
    this.op = op;
  }

  public boolean isPhase3Started() {
    return phase3Started;
  }

  public void setPhase3Started(boolean phase3Started) {
    this.phase3Started = phase3Started;
  }

  public boolean isPhase2Started() {
    return phase2Started;
  }

  public void setPhase2Started(boolean phase2Started) {
    this.phase2Started = phase2Started;
  }

  public boolean isMillCheckByPassed() {
    return millCheckByPassed;
  }

  public void setMillCheckByPassed(boolean millCheckByPassed) {
    this.millCheckByPassed = millCheckByPassed;
  }

  public boolean isPlayer2Computer() {
    return isPlayer2Computer;
  }

  public void setPlayer2Computer(boolean isPlayer2Computer) {
    this.isPlayer2Computer = isPlayer2Computer;
  }

  public ChoiceDialog<String> getChoiceDialog() {
    return choiceDialog;
  }

  public void setChoiceDialog(ChoiceDialog<String> choiceDialog) {
    this.choiceDialog = choiceDialog;
  }

  public TextInputDialog getName1Dialog() {
    return name1Dialog;
  }

  public void setName1Dialog(TextInputDialog name1Dialog) {
    this.name1Dialog = name1Dialog;
  }

  public TextInputDialog getName2Dialog() {
    return name2Dialog;
  }

  public void setName2Dialog(TextInputDialog name2Dialog) {
    this.name2Dialog = name2Dialog;
  }

  public List<Position> getEmptyNeighborsForComputer() {
    return emptyNeighborsForComputer;
  }

  public void setEmptyNeighborsForComputer(List<Position> emptyNeighborsForComputer) {
    this.emptyNeighborsForComputer = emptyNeighborsForComputer;
  }

  public List<Position> getNonMillCoinsForComputer() {
    return nonMillCoinsForComputer;
  }

  public void setNonMillCoinsForComputer(List<Position> nonMillCoinsForComputer) {
    this.nonMillCoinsForComputer = nonMillCoinsForComputer;
  }

  public Button getReplayGameButton() {
    return replayGameButton;
  }

  public void setReplayGameButton(Button replayGameButton) {
    this.replayGameButton = replayGameButton;
  }

  public Button getRecordGameButton() {
    return recordGameButton;
  }

  public void setRecordGameButton(Button recordGameButton) {
    this.recordGameButton = recordGameButton;
  }

  public boolean isRecordingEnabled() {
    return recordingEnabled;
  }

  public void setRecordingEnabled(boolean recordingEnabled) {
    this.recordingEnabled = recordingEnabled;
  }

public RecordReplayService getRecordReplayService(){return recordReplayService;}

public void setRecordReplayService(RecordReplayService recordReplayService){this.recordReplayService = recordReplayService;}
}
