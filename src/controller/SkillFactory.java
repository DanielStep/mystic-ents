package controller;

import java.util.HashMap;

import model.skills.AttackSkill;
import model.skills.BuildSkill;
import model.skills.HealSkill;
import model.skills.RangeSkill;
import model.skills.Skill;
import model.traits.Trait;

public class SkillFactory extends AbstractFactory {

	private static HashMap<String, Skill> skills = new HashMap<>();

	@Override
	public Trait makeTrait(String traitType) {
		return null;
	}

	@Override
	public Skill makeSkill(String skillType) {

		Skill sk = skills.get(skillType);

		if (skillType == null) {
			return null;
		}

		if (sk == null) {

			if (skillType.equalsIgnoreCase("ATTACK")) {
				sk = new AttackSkill();
			} else if (skillType.equalsIgnoreCase("RANGE")) {
				sk = new RangeSkill();
			} else if (skillType.equalsIgnoreCase("BUILD")) {
				sk = new BuildSkill();
			} else if (skillType.equalsIgnoreCase("HEAL")) {
				sk = new HealSkill();
			}
			skills.put(skillType, sk);
		}

		return sk;
	}

}
