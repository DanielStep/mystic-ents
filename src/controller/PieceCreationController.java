package controller;
import java.util.ArrayList;

import model.piece.Piece;
import model.piece.RegularPiece;
import model.piece.Team;
import model.piece.UsurperPiece;
import utils.GameConfig;

/**
 * PieceCreationController coordinates creation of Piece instances,
 * determines the number of different Pieces subtypes that are created
 * and returns them to the client in an ArrayList<Piece>
 * @author Daniel
 *
 */
public class PieceCreationController {
	
	private PieceBuilder pieceBuilder = new PieceBuilder();
	
	public PieceCreationController() {
		
	}
	
	/**
	 * Method adds appropriate number and type of Pieces from each team to pieceArrayList
	 * @return ArrayList<Piece>
	 */
	public ArrayList<Piece> generateGamePieces(){
		
		ArrayList<Piece> piecesArrayList = new ArrayList<Piece>();
		
		for(int i = 0; i < GameConfig.getUsurpersInTeam(); i++){
			piecesArrayList.add(createUsurpPiece(Team.BLUE));
			piecesArrayList.add(createUsurpPiece(Team.RED));
		}
		
		for(int i = 0; i < GameConfig.getRegularpiecesInTeam(); i++){
			piecesArrayList.add(createRegPiece(Team.BLUE));
			piecesArrayList.add(createRegPiece(Team.RED));	
		}
		
		return piecesArrayList;

	}
	
	/**
	 * Method given particular team enum, instantiates new regular piece and passes it
	 * to PieceBuilder for construction. Returns constructed new regular piece for insertion
	 * into piece array.
	 * 
	 * TODO Refactor for Builder Pattern, move Piece instantiation to PieceBuilder.
	 * @param team
	 * @return Piece
	 */
	public Piece createRegPiece(Enum<Team> team){
		
		RegularPiece newRegPiece = new RegularPiece();
		pieceBuilder.buildPiece(newRegPiece, team);
		
		return newRegPiece;
	}
	
	/**
	 * Method given particular team enum, instantiates new usurper piece and passes it
	 * to PieceBuilder for construction. Returns constructed new usurper piece for insertion
	 * into piece array.
	 * 
	 * TODO Refactor for Builder Pattern, move Piece instantiation to PieceBuilder.
	 * @param team
	 * @return Piece
	 */
	public Piece createUsurpPiece(Enum<Team> team){
		
		UsurperPiece newUsurpPiece = new UsurperPiece();
		pieceBuilder.buildPiece(newUsurpPiece, team);
		
		return newUsurpPiece;
	}

}
