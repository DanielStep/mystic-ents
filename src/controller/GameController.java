package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.GameTurn;
import model.Piece;
import model.Square;
import model.State;
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
	
	private static Piece activePiece;
	private static Piece targetPiece;
	private static Square activeSquare;
	private static Square targetSquare;
	private static State currentState;
	private Team currentTeam;

	private static ArrayList<Piece> gamePiecesList = new ArrayList<Piece>();
	
	public GameController() {
		currentState = State.STARTGAME;
		generateGamePieces();
		startTimer();
		currentState = State.STARTMOVE;
	}
	
	private void startTimer() {
		gameTimer = new GameTurn();
		observe(gameTimer);
		gameTimer.start();
	}
	
	public void generateGamePieces() {
		setGamePiecesList((new PieceCreationController()).generateGamePieces());
	}	
	
	public void computeWinner() {
		
	}
	
	public void observe(Observable o) {
		o.addObserver(this);
	}	
	
	@Override
	public void update(Observable o, Object arg) {
		GameTurn gameTurn = (GameTurn) o;
		if (gameTurn == null) return;
		int data = gameTurn.getGameTimer();

		// set end turn conditions
		EndTurnPanel endTurnPanel = controlPanel.getEndTurnPanel();
		endTurnPanel.setGameTurn(gameTurn);

		// update time on ControlPanel view
		TimePanel timePanel = controlPanel.getTimePanel();
		timePanel.setTime(data);
		
		// when time is up
		if (data == 0) {
			System.out.println("Player change!");
			
			//This stuff needs to move to a View class ?
			//or some kind of remote refresh method:
			
			// update team color on ControlPanel view
			TeamColorPanel teamColorPanel = controlPanel.getTeamColorPanel();
			Color colorChange = (teamColorPanel.getTeamColorEnum() == Team.BLUE) ? Color.RED : Color.BLUE;
			teamColorPanel.setTeamColor(colorChange);
			
			// auto end the current player's turn
			endTurnPanel.executeEndTurn();
			
			// reset timer
			startTimer();
			
			// reset player move
			GameController.setCurrentState(State.STARTMOVE);
		}
	}
	
	public static ArrayList<Piece> getGamePiecesList() {
		return gamePiecesList;
	}

	public static void setGamePiecesList(ArrayList<Piece> piecesList) {
		gamePiecesList = piecesList;
	}	
	
	public static State getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(State newState) {
		currentState = newState;
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

	public static Piece getActivePiece() {
		return activePiece;
	}

	public static void setActivePiece(Piece activePiece) {
		GameController.activePiece = activePiece;
	}

	public static Piece getTargetPiece() {
		return targetPiece;
	}

	public static void setTargetPiece(Piece targetPiece) {
		GameController.targetPiece = targetPiece;
	}

	public static Square getActiveSquare() {
		return activeSquare;
	}

	public static void setActiveSquare(Square activeSquare) {
		GameController.activeSquare = activeSquare;
	}

	public static Square getTargetSquare() {
		return targetSquare;
	}

	public static void setTargetSquare(Square targetSquare) {
		GameController.targetSquare = targetSquare;
	}

}