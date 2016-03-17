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

	public static final int ROW_COL = 30;

	/**
	 * Stores the locations of pieces and squares on the board.
	 */
	private Object[][] boardData = new Object[ROW_COL][ROW_COL];
	
	public Board() {

	}

	/**
	 * Used for debugging
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(", ");
		
		return str.toString();
	}

	public Object[][] getBoardData() {
		
		for (int i = 0; i < boardData.length; i++) {
			for (int j = 0; j < boardData[i].length; j++) {
				boardData[i][j] = null;// blank
			}
		}
		return boardData;
	}

}
