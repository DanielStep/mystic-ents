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
	public Object[][] boardData = new Object[ROW_COL][ROW_COL];
	
	public String obtest = "Observer test";
	
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
		for (int i = 0; i < boardData.length; i++) {
			for (int j = 0; j < boardData[i].length; j++) {
				boardData[i][j] = null;// blank
				if(i==2&&j==3){
					boardData[i][j] = 'x';
				}

				//System.out.println(".");
				boardData[i][j] = '.';
			}
		}
		setChanged();
	    notifyObservers(); // TODO: Pass the board data
	}
	
	public void setBoardCell(int x, int y, Object o) {
		boardData[x][y] = o;
		setChanged();
	    notifyObservers();
	}

	public Object[][] getBoardData() {
		return boardData;
	}
	
}
