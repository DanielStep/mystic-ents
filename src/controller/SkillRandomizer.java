package controller;

import java.util.ArrayList;
import java.util.Random;

import model.skills.Skill;
import model.skills.SkillSet;

public class SkillRandomizer {
	
	public SkillSet randomAllocationToSet(ArrayList<Skill> listOfSkills, Random randomNumGen){	
		int randomNum = randomNumGen.ints(0, listOfSkills.size()).limit(1).findFirst().getAsInt();		
		SkillSet skillSet = new SkillSet(listOfSkills.get(randomNum));		
		return skillSet;
	}

}
