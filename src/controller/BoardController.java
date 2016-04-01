package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.Board;
import model.Piece;
import view.BoardFrame;

public class BoardController implements Observer {

	private BoardFrame boardFrame;
	private Board boardState;
	private GameController gameController;
	
	public BoardController(GameController g) {
		
		boardState = new Board();
		observe(boardState);
		
		PieceActionController pac = new PieceActionController(boardState);
		
		// After we placed pieces inside boardState, initialize boardView
		boardFrame = new BoardFrame();
		boardFrame.getBoardPanel().setPac(pac);
		boardState.init();
		boardFrame.pack();
		
		// set team control in PieceActionController
		pac.setTeamColorPanel(boardFrame.getControlPanel().getTeamColorPanel());
		
		// set PieceActionController's game controller
		pac.setGameController(g);

		
		// Tell the View that when ever the calculate button
		// is clicked to execute the actionPerformed method
		// in the CalculateListener inner class

		// theView.addBoardListener(new BoardListener());

	}	

	public void observe(Observable o) {
		o.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		Object[][] data = ((Board) o).getBoardData();
		if (data == null) return;
		System.out.println("Board has changed: " + data.length);
		boardFrame.getBoardPanel().refreshBoard(data);
	}
	
	public Board getBoardState() {
		return boardState;
	}
	
	public BoardFrame getBoardFrame() {
		return boardFrame;
	}
}