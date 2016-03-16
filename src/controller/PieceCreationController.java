package controller;

import model.Board;
import view.OuterFrame;

public class PieceCreationController {
	/** Data **/
	private static Board dataBoard;

	/** GUI **/
	private static OuterFrame guiBoard;

	public PieceCreationController() {
		
		PieceActionController pac = new PieceActionController();
		
		// Model - BACK END logic
		dataBoard = new Board();
		
		// Model - FRONT END logic
		guiBoard = new OuterFrame(pac);
		guiBoard.start();
	}

}
