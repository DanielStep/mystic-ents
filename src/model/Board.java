package model;

import java.util.ArrayList;
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
	private Square[][] boardData = new Square[GameConfig.getRowCol()][GameConfig.getRowCol()];
	private ArrayList<Piece> gamePiecesList = new ArrayList<Piece>();
	
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
	
	public void clearRangeCells() {
		for(int i = 0; i < boardData.length; i++) {
			for(int j = 0; j < boardData.length; j++) {
				boardData[i][j].setInrange(false);
			}
		}
		doCellsUpdate();
	}
	public void setRangeCells(int x, int y) {
		int range = boardData[x][y].getOccupant().getTraitSet().getRangeTrait().getTraitValue();
		for(int i = (x-range); i < (x+(1+range)); i++) {
			if (i >= 0 && i < boardData.length) {
				for(int j = (y-range); j < (y+(1+range)); j++) {
					if (j >= 0 && j < boardData[i].length) {
						boardData[i][j].setInrange(true);
					}
				}				
			}

		}
		doCellsUpdate();
	}
	
	public void doCellsUpdate() {
		setChanged();
	    notifyObservers();
	}
	
	/*public Object getBoardCell(int x, int y) {
		return boardData[x][y];
	}*/

	public Square[][] getBoardData() {
		return boardData;
	}
	
	
	
}
