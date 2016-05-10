package model.piece;

import java.io.Serializable;

public abstract class PieceDecorator extends Piece implements Serializable{
	
	protected Piece decoratedPiece;
	
	public PieceDecorator (Piece decoratedPiece){
		this.decoratedPiece = decoratedPiece;
	}
	
	//should override all of Piece's methods, and inside them call the decorated piece's method

}
