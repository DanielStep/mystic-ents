package model.skills;

import model.board.Square;
import model.game.GameConfig;
import model.piece.Piece;
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
	
}
