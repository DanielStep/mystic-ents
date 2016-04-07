package model.skills;

import model.Piece;
import model.Square;

public interface IPerformTraitSkill {
	
	public void performSkill(Square aSqr, Square tSqr);
	public void applyModifier(Piece skillOwner);

}
