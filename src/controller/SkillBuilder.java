package controller;

import java.util.Random;

import model.skills.AttackSkill;
import model.skills.BuildSkill;
import model.skills.HealSkill;
import model.skills.SkillSet;
import model.skills.SpeedSkill;
import model.traits.TraitSet;

public class SkillBuilder {
	
	private Random randomNumGen;
	private SkillSet skillSet;
	
	public SkillBuilder(Random randomNumGen){
	
			this.randomNumGen = randomNumGen;
			generateSkillSetFromSkills();
		}

	private void generateSkillSetFromSkills() {
		
		HealSkill healSkill = new HealSkill();
		BuildSkill buildSkill = new BuildSkill();
		SpeedSkill speedSkill = new SpeedSkill();
		AttackSkill attackSkill = new AttackSkill();
		
	}
	
	

}
