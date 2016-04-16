package model.skills;


import model.board.Square;
import model.piece.Piece;
import utils.GameConfig;

/**
 * Class contains logic to increase the value of health trait of another piece of the same team by constant
 * @author Daniel
 *
 */
public class HealSkill extends Skill implements IPerformTraitSkill {

	public HealSkill() {
		// TODO Auto-generated constructor stub
		super.setName("Heal");
	}
	/**
	 * Method is passed target square and skill owner piece to check if there is occupant
	 * in the target square and whether occupant is same team as skill owner.
	 * If so, target occupant's healthtrait value is increase by constant.
	 * If not, exception is thrown.
	 * @param Square
	 * @param Piece
	 * return Boolean
	 */
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

}


class IncorrectSquareException extends RuntimeException { 
	
	public IncorrectSquareException() { 
		super(); 
	} 
	
	public IncorrectSquareException(String message) {
		super(message);
	}
	

}


