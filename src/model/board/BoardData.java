package model.board;

import java.io.Serializable;
import java.util.Observable;

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

	public BoardMemento saveToMemento() {
		BoardMemento boardMemento = new BoardMemento(this.boardArray);
		return boardMemento;
	}

	public void undoFromMemento(BoardMemento boardMemento) {
		this.setBoardArray(boardMemento.getBoardData());
		doCellsUpdate();
	}

	// For debugging
	public void print() {
		for (int i = 0; i < GameConfig.getROW_COL(); i++) {
			for (int j = 0; j < GameConfig.getROW_COL(); j++) {
				System.out.print(boardArray[i][j] + " ");
			}
			System.out.println();
		}
	}

}
