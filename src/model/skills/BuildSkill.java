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
		// TODO Auto-generated constructor stub
		super.setName("Build");
	}
	
	@Override
	public boolean performSkill(Square sqr, Piece skillOwner) {

		// If the square is empty and is accessible then build
		if(sqr.getOccupant()==null && sqr.getAccessible()){
			sqr.setAccessible(false);			
		}
		return !sqr.getAccessible();
	}

}
