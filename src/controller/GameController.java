package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.Board;
import model.BoardUtils;
import model.GameTurn;
import model.GameUtils;
import model.Piece;
import model.Square;
import model.State;
import model.Team;

import view.AvailablePiecePanel;
import view.ControlPanel;
import view.EndTurnPanel;
import view.PieceInfoPanel;
import view.TeamColorPanel;
import view.TimePanel;

/**
 * Responsible for turn handling and computing the winner team
 *
 * @author skh, ms, pv
 *
 */

public class GameController implements Observer {
	
	//GAME CONTROL
	private GameTurn gameTimer;
	private GameUtils gameUtils;
	private BoardController gameBoard;
	
	//UI
	private ControlPanel controlPanel;
	private TeamColorPanel teamColorPanel;
	private EndTurnPanel endTurnPanel;
	private TimePanel timePanel;
	private PieceInfoPanel pieceInfoPanel;
	
	//BOARD OBJECTS
	private Piece activePiece;
	private Piece targetPiece;
	private Square activeSquare;
	private Square targetSquare;
	private State currentState;
	private Team currentTeam;

	private static ArrayList<Piece> gamePiecesList = new ArrayList<Piece>();
	
	public GameController() {
		gameUtils = GameUtils.getInstance();
		currentState = State.STARTGAME;
		generateGamePieces();
		buildTimer();
		currentState = State.STARTMOVE;
		// TODO Randomise team selection 
		currentTeam = Team.BLUE;
	}
	
	public void generateGamePieces() {
		setGamePiecesList((new PieceCreationController()).generateGamePieces());
	}	

	public void setUIObjects(BoardController bd) {
		setControlPanel(bd.getBoardFrame().getControlPanel());
		teamColorPanel = controlPanel.getTeamColorPanel();
		endTurnPanel = controlPanel.getEndTurnPanel();
		timePanel = controlPanel.getTimePanel();
		pieceInfoPanel = controlPanel.getPieceInfoPanel();
		setGameBoard(bd);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		GameTurn gameTurn = (GameTurn) o;
		//Post condition exception if GameTuen is null, return (exit?)
		if (gameTurn == null) return;
		
		UIUpdate(gameTurn);
		
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
		UIEndTurn();
		
	}
	
	private void UIUpdate(GameTurn gameTurn) {
		// update time on ControlPanel view
		if (timePanel != null) {			
			timePanel.setTime(gameTurn.getGameTimer());
		}

		// set end turn conditions
		if (endTurnPanel != null) {
			endTurnPanel.setGameTurn(gameTurn);
		}

		// update available pieces for the current team 
		if (teamColorPanel != null) {
			int count = getAvailablePieceCount();
			controlPanel.getAvailablePiecePanel().setAvailablePieces(count);
		}
	}
	
	private void UIEndTurn() {
		// update team color on ControlPanel view based on current team enum
		if (teamColorPanel != null) {
			teamColorPanel.setTeamColor(gameUtils.stringToColor(currentTeam.name(), Color.BLACK));
		}
		
		//reset the Piece info panel on switch team.
		if (pieceInfoPanel != null) {
			pieceInfoPanel.resetPieceInformation();
		}
		
		// auto end the current player's turn
		if (endTurnPanel != null) {
			endTurnPanel.executeEndTurn();
		}
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
		
	public void updatePieceInformation(Piece pce) {
		// Update Piece Statistics on Selection
		pieceInfoPanel.updatePieceInformation(pce);
	}

	public static ArrayList<Piece> getGamePiecesList() {
		return gamePiecesList;
	}

	public static void setGamePiecesList(ArrayList<Piece> piecesList) {
		gamePiecesList = piecesList;
	}	
	
	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State newState) {
		this.currentState = newState;
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

	public Piece getActivePiece() {
		return activePiece;
	}

	public void setActivePiece(Piece activePiece) {
		this.activePiece = activePiece;
	}

	public Piece getTargetPiece() {
		return targetPiece;
	}

	public void setTargetPiece(Piece targetPiece) {
		this.targetPiece = targetPiece;
	}

	public Square getActiveSquare() {
		return activeSquare;
	}

	public void setActiveSquare(Square activeSquare) {
		this.activeSquare = activeSquare;
	}

	public Square getTargetSquare() {
		return targetSquare;
	}

	public void setTargetSquare(Square targetSquare) {
		this.targetSquare = targetSquare;
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