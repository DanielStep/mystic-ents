package controller;

import model.skills.AttackSkill;
import model.skills.BuildSkill;
import model.skills.HealSkill;
import model.skills.RangeSkill;
import model.skills.Skill;
import model.traits.AttackTrait;
import model.traits.DamageTrait;
import model.traits.HealthTrait;
import model.traits.RangeTrait;
import model.traits.Trait;

public class SkillFactory extends AbstractFactory {

	@Override
	public Trait makeTrait(String traitType) {
		return null;
	}

	@Override
	public Skill makeSkill(String skillType) {

		if (skillType == null) {
			return null;
		}

		if (skillType.equalsIgnoreCase("ATTACK")) {
			return new AttackSkill();
		} else if (skillType.equalsIgnoreCase("RANGE")) {
			return new RangeSkill();
		} else if (skillType.equalsIgnoreCase("BUILD")) {
			return new BuildSkill();
		} else if (skillType.equalsIgnoreCase("HEAL")) {
			return new HealSkill();
		}

		return null;
	}

}
