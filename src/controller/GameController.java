package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.game.GameTurn;
import model.piece.Piece;
import model.piece.Team;
import utils.GameUtils;
import view.ControlPanel;
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
	private PieceActionController pieceController;
	
	//UI
	private ControlPanel controlPanel;
	
	//BOARD OBJECTS
	private Team currentTeam = Team.BLUE;
	private static ArrayList<Piece> gamePiecesList = new ArrayList<Piece>();

	public GameController() {
		generateGamePieces();
		buildTimer();		
		new MainMenuFrame(this);
	}

	public void init() {
		gameBoard.buildBoard();
		gameBoard.getBoardFrame().setVisible(true);
		setControlObjects();
		startTimer();
	}
	
	public Boolean continueGame(){
		// TODO: load game here
		System.out.println("Load Game from the previous save...");
		Object gameState = GameUtils.getInstance().loadGame();
		if (gameState != null) {
			System.out.println("Load game successfully!");
			return true;
		} else {
			DialogView.getInstance().showInformation("Save game not found!");
			return false;
		}
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
	 * A turn is ended vis the timer value rather than calling a set function
	 * Gives better adherence to MVC pattern.
	 * 
	 */		
	private void handleEndTurn() {
		// set game turn count;
		int newCount = gameTimer.getCount();
		newCount++;
		gameTimer.setCount(newCount);
		
		//clear 'active' or piece specific board data;
		//gameBoard.getBoardState().clearRangeCells();
		
		//Change teams
		currentTeam = currentTeam == Team.BLUE ? Team.RED : Team.BLUE;

		//Update UI
		controlPanel.setCurrentTeam(currentTeam.name());
		controlPanel.doUIEndTurn();		
	}	
	
	private int getAvailablePieceCount() {
		int count = 0;
		for (Piece piece : gamePiecesList) {
			if (currentTeam == piece.getTeam()) {
				count++;
			}
		}
		return count;
	}
		
	public void updatePieceInformation(Piece pce) {
		// Update Piece Statistics on Selection
		controlPanel.getPieceInfoPanel().updatePieceInformation(pce);
	}
	
	private void generateGamePieces() {
		setGamePiecesList((new PieceCreationController()).generateGamePieces());
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

	public static ArrayList<Piece> getGamePiecesList() {
		return gamePiecesList;
	}

	public static void setGamePiecesList(ArrayList<Piece> piecesList) {
		gamePiecesList = piecesList;
	}	
	
	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}
	
	public void setBoardController(BoardController gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	public void setPieceActionController(PieceActionController pieceController) {
		this.pieceController = pieceController;
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

}