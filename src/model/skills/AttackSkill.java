package model.skills;

import model.board.Square;
import model.piece.Piece;
import utils.GameConfig;
/**
 * Class that increases damage of piece's attack action
 * @author Daniel
 *
 */
public class AttackSkill extends Skill implements Modifier {

	public AttackSkill() {
		// TODO Auto-generated constructor stub
		super.setName("Attack");
	}
	
	@Override
	public void applyModifier(Piece skillOwner) {		
		skillOwner.getTraitSet().getRangeTrait().modifyValue(GameConfig.getDamagetraitmultiplier());
	}
	
}
