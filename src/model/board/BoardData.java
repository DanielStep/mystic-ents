package model.board;

import java.io.Serializable;
import java.util.Observable;

import utils.GameConfig;

public class BoardData extends Observable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3623588786142287356L;

	private Square[][] boardArray = new Square[GameConfig.getROW_COL()][GameConfig.getROW_COL()];
	
	private static BoardData instance;
	
	private BoardData() {}	
	
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
	
	public void doCellsUpdate() {
		setChanged();
	    notifyObservers();
	}	
	
	public BoardMemento saveToMemento() {
		BoardMemento boardMemento = new BoardMemento(getBoardArray());
		return boardMemento;
	}
	
	public void undoFromMemento(BoardMemento boardMemento){
		this.boardArray = boardMemento.getBoardData();
		doCellsUpdate();
	}	
	
}
