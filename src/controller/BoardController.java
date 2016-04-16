package controller;

import java.util.Observable;
import java.util.Observer;

import model.board.BoardState;
import view.BoardFrame;

/**
 * Controller for board state
 * Generates the Board Model
 * Generates the Board View
 * Observes Board Model changes and calls View update
 * @author Mark
 *
 */

public class BoardController implements Observer {

	//PIECE CONTROLLER
	private PieceActionController pieceController;
	
	//VIEW
	private BoardFrame boardFrame;
	
	//MODEL
	private BoardState boardState;
	
	public BoardController() {
		boardState = new BoardState();
		boardFrame = new BoardFrame();
	}
	
	public void init() {
		boardState = new BoardState();
		boardFrame = new BoardFrame();
	}
	
	public void buildBoard() {
		boardFrame.getBoardPanel().setPac(pieceController);
		observe(boardState);
		boardState.init();
		boardFrame.pack();
	}

	public void observe(Observable o) {
		o.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		Object[][] data = ((BoardState) o).getBoardData();
		if (data == null) return;
		System.out.println("Updating Board...");
		boardFrame.getBoardPanel().refreshBoard(data);
	}
	
	public BoardState getBoardState() {
		return boardState;
	}
	
	public BoardFrame getBoardFrame() {
		return boardFrame;
	}
	
	public void setPieceActionController(PieceActionController pieceController) {
		this.pieceController = pieceController;
	}
}