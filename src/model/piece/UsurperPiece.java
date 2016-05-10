package model.piece;

import java.io.Serializable;

public class UsurperPiece extends PieceDecorator  implements Serializable{	
	
//	public UsurperPiece() {
//		super();
//		super.isUsurper = true;
//	}
	
	public UsurperPiece(Piece other) {
		super(other);
		super.isUsurper = true;
	}
	
	public String getIcon() {
		return "grim-reaper.png";
	}	

}
