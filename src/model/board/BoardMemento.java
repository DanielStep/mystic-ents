package model.board;

/**
 * Memento class
 * @author skh
 *
 */

public class BoardMemento {
	private Square[][] boardData;
	
	public BoardMemento(Square[][] boardData) {
		this.boardData = boardData;
	}
	
	public Square[][] getBoardData(){
		return this.boardData;
	}
}
