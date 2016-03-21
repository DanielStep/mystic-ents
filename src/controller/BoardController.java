package controller;

import java.util.Observable;
import java.util.Observer;

import model.Board;
import view.BoardFrame;
import view.BoardPanel;

public class BoardController implements Observer {

	private static BoardFrame boardFrame;
	private static BoardPanel boardView;
	private static Board boardState;

	public BoardController() {

		boardState = new Board();
		observe(boardState);
		
		// After we placed pieces inside boardState, initialize boardView
		boardFrame = new BoardFrame();
		boardView = new BoardPanel();		
		boardState.init();		
		boardFrame.add(boardView);
		boardFrame.pack();
		
		// boardState

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
		boardView.refreshBoard(data);
	}

}