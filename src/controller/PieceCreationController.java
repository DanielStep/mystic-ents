package controller;

import java.util.ArrayList;
import java.util.Random;

import model.Piece;
import model.RegularPiece;
import model.Team;
import model.UsurperPiece;
import view.BoardFrame;

public class PieceCreationController {
	
	final int REGULARPIECES_IN_TEAM = 7;
	final int USURPERS_IN_TEAM = 1;
	
	private PieceBuilder pieceBuilder = new PieceBuilder();

	public PieceCreationController() {
		
		PieceActionController pac = new PieceActionController();
		
	}
	
	public ArrayList<Piece> generateGamePieces(){
		
		ArrayList<Piece> piecesArrayList = new ArrayList<Piece>();
		
		for(int i = 0; i < REGULARPIECES_IN_TEAM; i++){
			piecesArrayList.add(createRegPiece(Team.BLUE));
			piecesArrayList.add(createRegPiece(Team.RED));	
		}
		
		for(int i = 0; i < USURPERS_IN_TEAM; i++){
			piecesArrayList.add(createUsurpPiece(Team.BLUE));
			piecesArrayList.add(createUsurpPiece(Team.RED));
		}
		
		return piecesArrayList;

	}
	
	public Piece createRegPiece(Enum<Team> team){
		
		RegularPiece newRegPiece = new RegularPiece();
		pieceBuilder.buildPiece(newRegPiece, team);
		
		return newRegPiece;
	}
	
	public Piece createUsurpPiece(Enum<Team> team){
		
		UsurperPiece newUsurpPiece = new UsurperPiece();
		pieceBuilder.buildPiece(newUsurpPiece, team);
		
		return newUsurpPiece;
	}

}
