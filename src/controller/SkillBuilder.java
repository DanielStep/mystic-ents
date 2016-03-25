package controller;

import java.util.Random;

import model.skills.AttackSkill;
import model.skills.BuildSkill;
import model.skills.HealSkill;
import model.skills.RangeSkill;
import model.skills.SkillSet;

public class SkillBuilder {

	private Random randomNumGen;
	private SkillSet skillSet;

	public SkillBuilder(Random randomNumGen) {

		this.randomNumGen = randomNumGen;
		generateSkillSetFromSkills();
	}

	private void generateSkillSetFromSkills() {

		HealSkill healSkill = new HealSkill();
		BuildSkill buildSkill = new BuildSkill();
		RangeSkill speedSkill = new RangeSkill();
		AttackSkill attackSkill = new AttackSkill();
		
		

	}

}
