package model.piece;

import java.io.Serializable;

public class RegularPiece extends Piece implements Serializable {

	public RegularPiece() {
		super();
	}
	
	public String getIcon() {
		return this.getSkillSet().getCurrentSkill().getIcon();
	}

}
