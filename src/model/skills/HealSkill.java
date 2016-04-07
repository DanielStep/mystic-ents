package model.skills;


import model.GameConfig;
import model.Piece;
import model.Square;


public class HealSkill extends Skill implements IPerformTraitSkill {

	final int HEALAMOUNT = 1;
	
	public HealSkill() {
		// TODO Auto-generated constructor stub
		super.setName("Heal");
	}
	
	@Override
	public void performSkill(Square aSqr, Square tSqr) {

		/*Test if square empty, then if square occupant in other team. If either, throw exception. 
		 * If neither increment occupants HealthTrait Value and set result to true*/
		
		Piece tPiece = tSqr.getOccupant();		
		try{
			if(tSqr.getOccupant() == null){
				throw new IncorrectSquareException("No piece in square.");
			}
			else{
				if (tPiece.getTeam() != aSqr.getOccupant().getTeam()){
					throw new IncorrectSquareException("No piece in square.");
				}
				else{					
					tPiece.getTraitSet().getHealthTrait().modifyValue(GameConfig.HEALAMOUNT);					
					//result = true;
				}
			}
		}
		catch(IncorrectSquareException e){
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void applyModifier(Piece skillOwner) {
		// TODO Auto-generated method stub
		
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


