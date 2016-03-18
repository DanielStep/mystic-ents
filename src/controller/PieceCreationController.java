package controller;

import model.Board;
import view.BoardFrame;

public class PieceCreationController {
	/** Data **/
	private static Board dataBoard;

	/** GUI **/
	private static BoardFrame guiBoard;

	public PieceCreationController() {
		
		PieceActionController pac = new PieceActionController();
		
		// Model - BACK END logic
		dataBoard = new Board();
		
		// Model - FRONT END logic
		//guiBoard = new BoardFrame(pac, dataBoard.getBoardData());
		//guiBoard.start();
	}

}
