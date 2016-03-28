package model.skills;

import model.Piece;
import model.Square;

// TODO: Implement the logic

public class HealSkill extends Skill implements IPerformSquareSkill {

	public boolean performSkill(Square sqr, Piece skillOwner) {
		
		Piece targetPiece;
		
		if(sqr.getOccupant() != null){
			
			targetPiece = sqr.getOccupant();
			
			if (targetPiece.getTeam() == skillOwner.getTeam()){
				
				
			}
			
		}
		
		
	}

}
