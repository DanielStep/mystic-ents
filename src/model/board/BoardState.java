package model.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import controller.GameController;
import model.piece.Piece;

/**
 * Entire board contains all pieces and squares
 * 
 * @author skh
 *
 */

//TODO: Game generator, type for piece for PieceView observer pattern
public class BoardState {

	/**
	 * Stores the locations of pieces and squares on the board.
	 */
	private ArrayList<Piece> gamePiecesList = new ArrayList<Piece>();
	
	private BoardData boardData;
	private BoardGenerator boardGenerator = new BoardGenerator();
	
	public BoardState() {		
		
	}

	public void init() {
		boardGenerator.loadMapData();
		//Get the pieces from GameController
		getPieces();
		//Populate BoardData from BoardGenerator
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
		Square[][] data = boardGenerator.generateStartBoard( gamePiecesList );
		boardData = BoardData.getInstance();
		boardData.setBoardData(data);
		boardData.doCellsUpdate();
	}
	
	public void getPieces() {
		gamePiecesList = new ArrayList<Piece>( GameController.getGamePiecesList() );
	}
	
}
