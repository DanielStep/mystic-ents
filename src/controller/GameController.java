package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.board.BoardData;
import model.board.Square;
import model.game.GameTurn;
import model.piece.Piece;
import model.piece.Team;
import utils.GameUtils;
import view.GameControls;
import view.DialogView;
import view.MainMenuFrame;

/**
 * Responsible for turn handling
 * Via Timer and end turn handling
 * And managing UI view
 * 
 * Generate game pieces that will be passed to the Board
 *
 * @author skh, mark, pv
 *
 */

public class GameController implements Observer {

	//GAME CONTROL
	private GameTurn gameTimer;
	private BoardController gameBoard;
	private ActionController actionController;
	private AIController gameAI;
	private Boolean aiTurn;

	//UI
	private GameControls controlPanel;
	
	//BOARD OBJECTS
	private Team currentTeam;
	
	private static ArrayList<Piece> gamePiecesList = new ArrayList<Piece>();

	public GameController() {
		buildTimer();
		new MainMenuFrame(this);
	}

	public void startGame() {
		gameBoard.init();
		continueGame();
	}
	
	public void continueGame() {
		gameBoard.buildBoard();
		gameBoard.getBoardFrame().setVisible(true);
		collectGamePieces();
		setControlObjects();
		buildAI();
		currentTeam = setCurrentTeam();
		startTimer();
	}
	
	private void buildAI() {
		// TODO Auto-generated method stub
		gameAI = new AIController(gamePiecesList);
	}

	/**
	 * The update method of the Observer pattern
	 * Watches the GameTurn timer to handle end turns, team change,
	 * And to update the UI
	 */	
	
	@Override
	public void update(Observable o, Object arg) {
		GameTurn gameTurn = (GameTurn) o;
		//Post condition exception if GameTurn is null, return (exit?)
		if (gameTurn == null) return;		
			controlPanel.setPieceCount(getAvailablePieceCount());
			controlPanel.doUIUpdate(gameTurn);
		// when time is up
		if (gameTurn.getGameTimer() == 0) {
			handleEndTurn();
		}
	}

	/**
	 * EndTurn handler
	 * Called via update method when GameTurn.getGameTimer = 0
	 * A turn is ended via the timer value rather than calling a set function
	 * Gives better adherence to MVC pattern.
	 * 
	 */		
	private void handleEndTurn() {
		//reset action counter
		ActionController.getInstance().resetActionCount();
		// set game turn count;
		gameTimer.setCount(gameTimer.getCount()+1);
		//Reset Board
		gameBoard.clearRangeCells();
		//Change teams
		currentTeam = changeTeams();
		gameBoard.getBoardData().setCurrentTeam(currentTeam);
		//Reset TraitValues of all pieces on board to base value
		gameBoard.getBoardData().resetPieceTraitValueToBase(gamePiecesList);
		//Update UI
		controlPanel.setCurrentTeam(currentTeam);
		controlPanel.setPieceCount(getAvailablePieceCount());
		controlPanel.doUIEndTurn();
		//Check AI Status
		aiTurn = gameAI.checkAIStatus(currentTeam);
	}
	
	public Boolean loadGame(){
		BoardData data = GameUtils.getInstance().loadGame();
		if (GameUtils.getInstance().loadGame() != null) {
			gameBoard.getBoardData().setCurrentTeam(data.getCurrentTeam());
			gameBoard.getBoardData().setBoardArray(data.getBoardArray());
			gameBoard.getBoardData().doCellsUpdate();
			return true;
		} else {
			DialogView.getInstance().showInformation("Save game not found!");
			return false;
		}
	}
	
	private Team changeTeams() {
		return GameUtils.getInstance().getNextTeam(gamePiecesList, currentTeam);
	}
	
	private Team setCurrentTeam() {
		Team team = gameBoard.getBoardData().getCurrentTeam();
		team = team == null ? Team.values()[0] : team;		
		controlPanel.setCurrentTeam(team);
		return team;
	}

	private int getAvailablePieceCount() {
		return GameUtils.getInstance().getAvailablePieceCount(gamePiecesList, currentTeam);
	}
		
	public void updatePieceInformation(Piece pce) {
		// Update Piece Statistics on Selection
		controlPanel.getPieceInfoPanel().updatePieceInformation(pce);
	}
	
	private void collectGamePieces() {
		Square[][] gameData = gameBoard.getBoardData().getBoardArray();
		for (int i=0; i<gameData.length; i++) {
			for (int j=0; j<gameData[i].length; j++) {
				if (gameData[i][j].getOccupant() != null) {
					gamePiecesList.add(gameData[i][j].getOccupant());
				}				
			}
		}
	}

	public void setControlObjects() {
		setControlPanel(gameBoard.getBoardFrame().getControlPanel());
	}	
	
	private void buildTimer() {
		gameTimer = new GameTurn();
		observe(gameTimer);
	}
	
	public void startTimer() {
		gameTimer.start();
	}
	public void stopTimer() {
		gameTimer.stop();
	}
	
	public void observe(Observable o) {
		o.addObserver(this);
	}

	public ArrayList<Piece> getGamePiecesList() {
		return gamePiecesList;
	}

	public void setGamePiecesList(ArrayList<Piece> piecesList) {
		gamePiecesList = piecesList;
	}	
	
	public void setControlPanel(GameControls controlPanel) {
		this.controlPanel = controlPanel;
	}
	
	public void setBoardController(BoardController gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	public void setPieceActionController(ActionController actionController) {
		this.actionController = actionController;
	}
	
	public Team getCurrentTeam() {
		return currentTeam;
	}

	public void setCurrentTeam(Team currentTeam) {
		this.currentTeam = currentTeam;
	}

	public GameTurn getGameTurn() {
		return gameTimer;
	}

	public BoardController getGameBoard() {
		return gameBoard;
	}	
	
	public Boolean getAiTurn() {
		return aiTurn;
	}

	public void setAiTurn(Boolean aiTurn) {
		this.aiTurn = aiTurn;
	}
}