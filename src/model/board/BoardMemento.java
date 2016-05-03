package model.board;

import utils.GameConfig;

/**
 * Memento class
 * 
 * @author skh
 *
 */

public class BoardMemento {
	private final Square[][] boardData = new Square[GameConfig.getROW_COL()][GameConfig.getROW_COL()];

	public BoardMemento() {
	}

	public BoardMemento(Square[][] boardData) {
		for (int i = 0; i < GameConfig.getROW_COL(); i++) {
			for (int j = 0; j < GameConfig.getROW_COL(); j++) {
				this.boardData[i][j] = new Square(boardData[i][j]);
			}
		}
	}

	public Square[][] getBoardData() {
		return this.boardData;
	}

	// For debugging
	public void print() {
		for (int i = 0; i < GameConfig.getROW_COL(); i++) {
			for (int j = 0; j < GameConfig.getROW_COL(); j++) {
				System.out.print(boardData[i][j] + " ");
			}
			System.out.println();
		}
	}
}
