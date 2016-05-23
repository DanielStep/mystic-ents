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
	public Skill makeSkill(String skillType) {

		Skill sk = skills.get(skillType);

		if (skillType == null) {
			return null;
		}

		if (sk == null) {

			switch (skillType) {
			case "ATTACK":
				sk = new AttackSkill();
				break;
			case "RANGE":
				sk = new RangeSkill();
				break;
			case "BUILD":
				sk = new BuildSkill();
				break;
			case "HEAL":
				sk = new HealSkill();
				break;
			}
			skills.put(skillType, sk);
		}

		return sk;
	}

	
	@Override
	public Trait makeTrait(String traitType) {
		return null;
	}
}
