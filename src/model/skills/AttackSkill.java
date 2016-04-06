package model.skills;

import model.Piece;

public class AttackSkill extends Skill implements IPerformTraitSkill {

	final int DAMAGETRAITMULTIPLIER = 2;

	public AttackSkill() {
		// TODO Auto-generated constructor stub
		super.setName("<html>Attack <br>amplifier</html>");
	}
	
	@Override
	public void performSkill(Piece skillOwner) {
		
		skillOwner.getTraitSet().getDamageTrait().modifyValue(DAMAGETRAITMULTIPLIER);
		
	}

}
