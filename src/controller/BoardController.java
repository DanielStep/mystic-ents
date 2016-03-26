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
		
	public BoardController() {
		
		boardState = new Board();
		observe(boardState);

		// After we placed pieces inside boardState, initialize boardView
		boardFrame = new BoardFrame();
//		boardView = new BoardPanel(); // BoardPanel can	be directly initialized from BoardFrame, creating it inside controller adds more coupling ?
		boardState.init();
//		boardFrame.add(boardView);
		boardFrame.pack();
		
		
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
	
}