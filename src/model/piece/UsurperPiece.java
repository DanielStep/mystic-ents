package model.piece;

import java.io.Serializable;

public class UsurperPiece extends Piece  implements Serializable{	
	
	public UsurperPiece() {
		super();
		super.isUsurper = true;
	}
	
	public String getIcon() {
		return "grim-reaper.png";
	}	

}
