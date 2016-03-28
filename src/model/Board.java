package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import controller.GameController;

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
	private ArrayList<Piece> gamePiecesList = new ArrayList<Piece>();
	//private static GameController gameState;

	public void init() {
		getPieces();
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
		boardData = boardGen.generateStartBoard( gamePiecesList );
		setChanged();
	    notifyObservers(); // TODO: Pass the board data
	}
	
	public void getPieces() {
		gamePiecesList = new ArrayList<Piece>( GameController.getGamePiecesList() );
	}
	
	public void doCellsUpdate() {
		setChanged();
	    notifyObservers();
	}
	
	public void setBoardCell(int x, int y, Object o) {
		boardData[x][y] = o;
		setChanged();
	    notifyObservers();
	}
	
	/*public Object getBoardCell(int x, int y) {
		return boardData[x][y];
	}*/

	public Object[][] getBoardData() {
		return boardData;
	}
	
	
	
}
