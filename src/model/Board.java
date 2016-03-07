package model;

import java.awt.Color;

/**
 * Responsible for doing majority of back-end work. It does not display
 * 
 * @author skh
 *
 */
public class Board {
	
	public static final int ROWCOL = 30;

	/**
	 * Stores the data of characters on the board.
	 */
	private char[][] boardData = new char[ROWCOL][ROWCOL];

	private MyCharacter character;

	/**
	 * Constructor of the class
	 */
	public Board() {
		// setup the characters
		character = new MyCharacter(10, 10, Color.GRAY);
	}

	/**
	 * Used for debugging
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(character.getcX());
		str.append(", ");
		str.append(character.getcY());
		return str.toString();
	}

	public char[][] getBoardData() {
		int cx = character.getcX();
		int cy = character.getcY();

		for (int i = 0; i < boardData.length; i++) {
			for (int j = 0; j < boardData[i].length; j++) {
				if (cx == i && cy == j) {
					boardData[i][j] = 'x'; // Character
				}
				boardData[i][j] = '.';// blank
			}
		}
		return boardData;
	}

}
