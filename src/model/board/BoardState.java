package model.board;

import java.util.ArrayList;
import java.util.Observable;

import controller.GameController;
import model.piece.Piece;
import utils.BoardUtils;
import utils.GameConfig;

/**
 * Entire board contains all pieces and squares
 * 
 * @author skh
 *
 */

//TODO: Game generator, type for piece for PieceView observer pattern
public class BoardState extends Observable {

	/**
	 * Stores the locations of pieces and squares on the board.
	 */
	private Square[][] boardData = new Square[GameConfig.getROW_COL()][GameConfig.getROW_COL()];
	private ArrayList<Piece> gamePiecesList = new ArrayList<Piece>();
	
	private BoardGenerator boardGen = new BoardGenerator();
	private BoardUtils boardUtils;
	
	public BoardState() {
		boardUtils = BoardUtils.getInstance();
		boardGen.loadMapData();	
	}
	
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
		boardData = boardGen.generateStartBoard( gamePiecesList );
		setChanged();
	    notifyObservers(); //Pass the board data to view
	}
	
	public void getPieces() {
		gamePiecesList = new ArrayList<Piece>( GameController.getGamePiecesList() );
	}
	
	public void clearRangeCells() {
		boardData = boardUtils.clearRangeCells(boardData);
		doCellsUpdate();
	}
	
	public void getRangeCells(int x, int y) {
		boardData = boardUtils.getRangeCells(x, y, boardData);
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
	
	public BoardMemento saveToMemento() {
		BoardMemento boardMemento = new BoardMemento(getBoardData());
		return boardMemento;
	}
	
	public void undoFromMemento(BoardMemento boardMemento){
		this.boardData = boardMemento.getBoardData();
		doCellsUpdate();
	}
	
}
