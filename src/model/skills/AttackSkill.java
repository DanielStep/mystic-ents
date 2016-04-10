package model.skills;

import model.GameConfig;
import model.Piece;
import model.Square;
/**
 * Class that increases damage of piece's attack action
 * @author Daniel
 *
 */
public class AttackSkill extends Skill implements IPerformTraitSkill {

	public AttackSkill() {
		// TODO Auto-generated constructor stub
		super.setName("Attack");
	}
	
	@Override
	public void performSkill(Square aSqr, Square tSqr) {		
		tSqr.getOccupant().getTraitSet().getDamageTrait().modifyValue(GameConfig.DAMAGETRAITMULTIPLIER);
	}
	
	@Override	
	public void applyModifier(Piece skillOwner) {
		
	}
	
}
