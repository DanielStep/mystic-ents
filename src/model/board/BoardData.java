package model.board;

import java.io.Serializable;
import java.util.Observable;

import utils.BoardUtils;
import utils.GameConfig;

public class BoardData extends Observable implements Serializable {
	
	private Square[][] boardDataArray = new Square[GameConfig.getROW_COL()][GameConfig.getROW_COL()];
	
	private static BoardData instance;
	
	public static synchronized BoardData getInstance() {
		if (instance == null) {
			instance = new BoardData();
		}
		return instance;
	}	
	
	private BoardData() {}	

	public void setBoardData(Square[][] data) {
		boardDataArray = data;
	}
	
	public Square[][] getBoardData() {
		return boardDataArray;
	}	
	
	public void doCellsUpdate() {
		setChanged();
	    notifyObservers();
	}	
	
	public BoardMemento saveToMemento() {
		BoardMemento boardMemento = new BoardMemento(getBoardData());
		return boardMemento;
	}
	
	public void undoFromMemento(BoardMemento boardMemento){
		this.boardDataArray = boardMemento.getBoardData();
		doCellsUpdate();
	}	
	
}
