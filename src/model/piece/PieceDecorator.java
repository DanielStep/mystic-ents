package model.piece;

import java.io.Serializable;

public abstract class PieceDecorator implements Piece, Serializable{
	
	protected Piece decoratedPiece;
	
	public PieceDecorator (Piece decoratedPiece){
		this.decoratedPiece = decoratedPiece;
	}
	
}
