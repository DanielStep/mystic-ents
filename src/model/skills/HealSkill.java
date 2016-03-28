package model.skills;


import model.Piece;
import model.Square;


public class HealSkill extends Skill implements IPerformSquareSkill {

	final int HEALAMOUNT = 1;
	
	public boolean performSkill(Square sqr, Piece skillOwner) {
		
		boolean result = false;
		Piece targetPiece;
		
		try{
			if(sqr.getOccupant() == null){
				throw new IncorrectSquareException("No piece in square.");
			}
			else{
				targetPiece = sqr.getOccupant();
				
				if (targetPiece.getTeam() != skillOwner.getTeam()){
					
					throw new IncorrectSquareException("No piece in square.");
				}
				else{
					targetPiece.getTraitSet().getHealthTrait().modifyValue(HEALAMOUNT);	
					result = true;
				}
			}
		}
	catch(IncorrectSquareException e){
		 System.out.println(e.getMessage());
		}
		return result;
	}
}


class IncorrectSquareException extends RuntimeException { 
	
	public IncorrectSquareException() { 
		super(); 
	} 
	
	public IncorrectSquareException(String message) {
		super(message);
	}
	

}


