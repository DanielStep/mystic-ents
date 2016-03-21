package model;

import java.util.Observable;

/**
 * Entire board contains all pieces and squares
 * 
 * @author skh
 *
 */

//TODO: Game generator, type for piece for PieceView observer pattern
public class Board extends Observable {

	/**
	 * Stores the locations of pieces and squares on the board.
	 */
	private Object[][] boardData = new Object[GameConfig.getRowCol()][GameConfig.getRowCol()];

	public Board() {
		//init();
	}

	public void init() {
		initBoardData();
	}
	
	/**
	 * Used for debugging
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(", ");
		return str.toString();
	}
	
	public void initBoardData() {
		BoardGenerator boardGen = new BoardGenerator();
		boardData = boardGen.generateStartBoard();
		setChanged();
	    notifyObservers(); // TODO: Pass the board data
	}
	
	public void setBoardCell(int x, int y, Object o) {
		boardData[x][y] = o;
		setChanged();
	    notifyObservers();
	}
	
	public Object getBoardCell(int x, int y) {
		return boardData[x][y];
	}	

	public Object[][] getBoardData() {
		return boardData;
	}
	
}
