package controller;

import java.util.Observable;
import java.util.Observer;

import model.Board;

import view.BoardFrame;

/**
 * Controller for board state
 * Generates the Board Model
 * Generates the Board View
 * Observes Board Model changes and calls view update
 * @author Mark
 *
 */

public class BoardController implements Observer {

	private BoardFrame boardFrame;
	private Board boardState;
	
	public BoardController(GameController g) {
		
		boardState = new Board();
		observe(boardState);
		
		PieceActionController pac = new PieceActionController(this);
		// set PieceActionController's game controller
		pac.setGameController(g);	
		
		// After we placed pieces inside boardState, initialize boardView
		boardFrame = new BoardFrame();
		boardFrame.getBoardPanel().setPac(pac);
		boardState.init();
		boardFrame.pack();		

	}

	public void observe(Observable o) {
		o.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		Object[][] data = ((Board) o).getBoardData();
		if (data == null) return;
		System.out.println("Updating Board...");
		boardFrame.getBoardPanel().refreshBoard(data);
	}
	
	public Board getBoardState() {
		return boardState;
	}
	
	public BoardFrame getBoardFrame() {
		return boardFrame;
	}
}