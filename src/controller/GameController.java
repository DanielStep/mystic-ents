package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.GameTurn;
import model.Piece;
import model.Team;
import view.ControlPanel;
import view.EndTurnPanel;
import view.TeamColorPanel;
import view.TimePanel;

/**
 * Responsible for turn handling and computing the winner team
 *
 * @author skh, ms, pv
 *
 */

public class GameController implements Observer {

	private static GameTurn gameTimer;	
	private static BoardController gameBoard;
	private ControlPanel controlPanel;
	
	private enum moveState {
		STARTGAME,
		STARTMOVE,
		ENDMOVE,
		ENDGAME
	}

	private moveState currentState;

	private static ArrayList<Piece> gamePiecesList = new ArrayList<Piece>();
	
	public GameController() {
		currentState = moveState.STARTGAME;
		generateGamePieces();
		startTimer();
	}
	
	private void startTimer() {
		gameTimer = new GameTurn();
		observe(gameTimer);
		gameTimer.start();
	}
	
	public void generateGamePieces() {
		setGamePiecesList((new PieceCreationController()).generateGamePieces());
		
		System.out.println("Generated Pieces: " + gamePiecesList.size());
		
	}	
	
	public void computeWinner() {
		
	}
	
	public void observe(Observable o) {
		o.addObserver(this);
	}	
	
	@Override
	public void update(Observable o, Object arg) {
		int data = ((GameTurn) o).getGameTimer();

		System.out.println("Time remaining: " + data);
		// update time on ControlPanel view
		TimePanel timePanel = controlPanel.getTimePanel();
		timePanel.setTime(data);
		
		// when time is up
		if (data == 0) {
			System.out.println("Player change!");
			// update team color on ControlPanel view
			TeamColorPanel teamColorPanel = controlPanel.getTeamColorPanel();
			Color colorChange = (teamColorPanel.getCurrentColorEnum() == Team.BLUE) ? Color.RED : Color.BLUE;
			teamColorPanel.setTeamColor(colorChange);
			
			// auto end the current player's turn
			EndTurnPanel endTurnPanel = controlPanel.getEndTurnPanel();
			endTurnPanel.executeEndTurn();
			
			// reset timer
			startTimer();
		}
	}
	
	public static ArrayList<Piece> getGamePiecesList() {
		return gamePiecesList;
	}

	public void setGamePiecesList(ArrayList<Piece> gamePiecesList) {
		this.gamePiecesList = gamePiecesList;
	}	
	
	public moveState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(moveState currentState) {
		this.currentState = currentState;
	}	
	
	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}
}