package model.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import model.piece.Piece;
import model.piece.Team;
import utils.GameConfig;

public class BoardData extends Observable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3623588786142287356L;

	private Square[][] boardArray = new Square[GameConfig.getROW_COL()][GameConfig.getROW_COL()];

	private static BoardData instance;

	private Team currentTeam = null;
	
	private boolean isWithAI = false;
	
	private HashMap<Team, Boolean> teamUndo = new HashMap<Team, Boolean>();

	private BoardData() {
	}

	public static synchronized BoardData getInstance() {
		if (instance == null) {
			instance = new BoardData();
		}
		return instance;
	}

	public void setBoardArray(Square[][] data) {
		boardArray = data;
	}

	public Square[][] getBoardArray() {
		return boardArray;
	}

	public void setIsWithAI(boolean isWithAI) {
		this.isWithAI = isWithAI;
	}

	public boolean getIsWithAI() {
		return isWithAI;
	}

	public void setTeamUndo(Team t, Boolean isUndo) {
		teamUndo.put(t, isUndo);
	}

	public HashMap<Team, Boolean> getTeamUndo() {
		return teamUndo;
	}
	
	public void setCurrentTeam(Team team) {
		this.currentTeam = team;
	}

	public Team getCurrentTeam() {
		return currentTeam;
	}
	
	public void doCellsUpdate() {
		setChanged();
		notifyObservers();
	}

	// For debugging
	public void print() {
		/*for (int i = 0; i < GameConfig.getROW_COL(); i++) {
			for (int j = 0; j < GameConfig.getROW_COL(); j++) {
				System.out.print(boardArray[i][j] + " ");
			}
			System.out.println();
		}*/
	}

}
