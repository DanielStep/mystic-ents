package model.skills;


import model.Piece;
import model.Square;


public class HealSkill extends Skill implements IPerformSquareSkill {

	final int HEALAMOUNT = 1;
	
	public HealSkill() {
		// TODO Auto-generated constructor stub
		super.setName("Heal");
	}
	
	public boolean performSkill(Square sqr, Piece skillOwner) {
		
		boolean result = false;
		Piece targetPiece;
		
		/*Test if square empty, then if square occupant in other team. If either, throw exception. 
		 * If neither increment occupants HealthTrait Value and set result to true*/
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


