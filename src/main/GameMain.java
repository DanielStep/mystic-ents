package main;

import model.Board;
import view.BoardView;

public class GameMain implements Runnable {
	/** Data **/
	private static Board dataBoard;
	/** GUI **/
	private static BoardView guiBoard;

	public static void main(String[] args) {
		// Model - BACK END logic
		dataBoard = new Board();		
		System.out.println(dataBoard);
		
		// Model - FRONT END logic
		guiBoard = new BoardView();
		guiBoard.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
