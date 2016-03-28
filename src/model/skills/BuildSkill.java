package model.skills;

import model.Piece;
import model.Square;

public class BuildSkill extends Skill implements IPerformSquareSkill {


	@Override
	public boolean performSkill(Square sqr, Piece skillOwner) {

		// If the square is empty and is accessible then build
		if(sqr.getOccupant()==null && sqr.getAccessible()){
			sqr.setAccessible(false);			
		}
		return !sqr.getAccessible();
	}

}
