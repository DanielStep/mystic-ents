package model.skills;

import model.Piece;
import model.Square;
/**
 * Interface for all skills that perform actions on traits
 * @author Daniel
 *
 */
public interface IPerformTraitSkill {
	
	public void performSkill(Square aSqr, Square tSqr);
	

}
