package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import model.Board;
import model.skills.AttackSkill;
import model.skills.BuildSkill;
import model.skills.HealSkill;
import model.skills.RangeSkill;
import model.skills.Skill;
import model.skills.SkillSet;

public class SkillBuilder implements Observer {

	private Random randomNumGen;
	private SkillSet skillSet;

	public SkillBuilder(Random randomNumGen) {
		this.randomNumGen = randomNumGen;
		generateSkillSetFromSkills();
	}

	/*
	 * Need to refactor, break up method
	 * */
	private void generateSkillSetFromSkills() {

		HealSkill healSkill = new HealSkill();
		BuildSkill buildSkill = new BuildSkill();
		RangeSkill speedSkill = new RangeSkill();
		AttackSkill attackSkill = new AttackSkill();
		
		ArrayList<Skill> listOfSkills = new ArrayList<Skill>();
		
		listOfSkills.add(healSkill);
		listOfSkills.add(buildSkill);
		listOfSkills.add(speedSkill);
		listOfSkills.add(attackSkill);
		
		SkillRandomizer skillRandomizer = new SkillRandomizer();
		
		skillSet = skillRandomizer.randomAllocationToSet(listOfSkills, randomNumGen);

	}
	
	public SkillSet getSkillSet() {
		return skillSet;
	}

	public void observe(Observable o) {
		o.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		Object[][] data = ((Board) o).getBoardData();

	}
}
