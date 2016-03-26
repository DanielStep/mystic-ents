package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.GameTurn;
import model.Piece;

/**
 * Responsible for turn handling and computing the winner team
 *
 * @author skh, ms, pv
 *
 */

public class GameController implements Observer {

	private static GameTurn gameTimer;	
	private static BoardController gameBoard;
	
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
		//System.out.println("Time remaining: " + data);
		if (data == 0) {
			//System.out.println("Player change!");
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

}