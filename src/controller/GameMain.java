package controller;

import model.Board;
import view.GameBoard;

public class GameMain {
	/** Data **/
	private static Board dataBoard;
	/** GUI **/
	private static GameBoard guiBoard;

	public static void main(String[] args) {
		// Model - BACK END logic
		dataBoard = new Board();		
		System.out.println(dataBoard);
		
		// Model - FRONT END logic
		guiBoard = new GameBoard();
		guiBoard.start();
	}
}
