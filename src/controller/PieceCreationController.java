package controller;

import java.util.ArrayList;

import model.*;
import view.BoardFrame;

public class PieceCreationController {
	
	final int REGULARPIECES_IN_TEAM = 8;
	final int USURPERS_IN_TEAM = 1;
	
	/** TODO Lets remove the static game board when we have finished the BoardController class **/
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
	
	public ArrayList<Piece> generateGamePieces(){
		
		PieceBuilder pieceBuilder = new PieceBuilder();
		ArrayList<Piece> piecesArrayList = new ArrayList<Piece>();
		
		for(int i = 0; i < REGULARPIECES_IN_TEAM; i++){
			
			RegularPiece newPiece = new RegularPiece();
			pieceBuilder.buildPiece(newPiece);
			piecesArrayList.add(newPiece);
		}
		
		for(int i = 0; i < USURPERS_IN_TEAM; i++){
			
			UsurperPiece newPiece = new UsurperPiece();
			pieceBuilder.buildPiece(newPiece);
			piecesArrayList.add(newPiece);
		}
		
		
		return piecesArrayList;

	}

}
