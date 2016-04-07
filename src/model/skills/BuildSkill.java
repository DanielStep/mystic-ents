package model.skills;

import model.Piece;
import model.Square;

/**
 * Make square inaccessible
 * @author skh
 *
 */

public class BuildSkill extends Skill implements IPerformSquareSkill {

	public BuildSkill() {
		super.setName("Build");
	}
	
	@Override
	public boolean performSkill(Square aSqr, Square tSqr) {

		// If the square is empty and is accessible then build
		if (tSqr.getOccupant()==null && tSqr.getAccessible()) {
			tSqr.setAccessible(false);
		}
		return !tSqr.getAccessible();
		
	}

}
