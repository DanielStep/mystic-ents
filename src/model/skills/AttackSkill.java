package model.skills;

import model.Piece;

public class AttackSkill extends Skill implements IPerformTraitSkill {

	final int DAMAGETRAITMULTIPLIER = 2;

	@Override
	public void performSkill(Piece skillOwner) {
		
		skillOwner.getTraitSet().getDamageTrait().modifyValue(DAMAGETRAITMULTIPLIER);
		
	}

}
