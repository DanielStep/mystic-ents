package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.GameTurn;
import model.Piece;
import model.Team;

import view.ControlPanel;

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
	
	//UI
	private ControlPanel controlPanel;
	
	//BOARD OBJECTS
	private Team currentTeam;

	private static ArrayList<Piece> gamePiecesList = new ArrayList<Piece>();
	
	public GameController() {
		generateGamePieces();
		buildTimer();
		// TODO Randomise team selection 
		currentTeam = Team.BLUE;
	}
	
	private void generateGamePieces() {
		setGamePiecesList((new PieceCreationController()).generateGamePieces());
	}	

	public void setUIObjects(BoardController bd) {
		setControlPanel(bd.getBoardFrame().getControlPanel());
		setGameBoard(bd);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		GameTurn gameTurn = (GameTurn) o;
		//Post condition exception if GameTuen is null, return (exit?)
		if (gameTurn == null) return;
		
		controlPanel.setPieceCount(getAvailablePieceCount());
		controlPanel.doUIUpdate(gameTurn);
		
		// when time is up
		if (gameTurn.getGameTimer() == 0) {
			handleEndTurn();
		}
		
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
	
	private void handleEndTurn() {
		// set game turn count;
		int newCount = gameTimer.getCount();
		newCount++;
		gameTimer.setCount(newCount);
		
		//clear 'active' or piece specific board data;
		gameBoard.getBoardState().clearRangeCells();
		
		//Change teams
		currentTeam = currentTeam == Team.BLUE ? Team.RED : Team.BLUE;

		//Update UI
		controlPanel.setCurrentTeam(currentTeam.name());
		controlPanel.doUIEndTurn();
		
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

	public void setGameBoard(BoardController gameBoard) {
		this.gameBoard = gameBoard;
	}

}