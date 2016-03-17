package model;

/**
 * Responsible for doing majority of back-end work. It does not display
 * 
 * @author skh
 *
 */

//TODO: Game generator, type for piece for PieceView observer pattern
public class Board {

	public static final int ROW_COL = 30;

	/**
	 * Stores the data of characters on the board.
	 */
	private Object[][] boardData = new Object[ROW_COL][ROW_COL];

	/**
	 * Constructor of the class
	 */
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
