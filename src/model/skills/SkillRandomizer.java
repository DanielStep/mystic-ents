package model.skills;

import java.util.ArrayList;
import java.util.Random;

public class SkillRandomizer {
	
	/*public Skillset randomAllocationToSet(ArrayList<Skill> listOfSkills, Random randomNumGen){	
		int randomNum = randomNumGen.ints(0, listOfSkills.size()).limit(1).findFirst().getAsInt();
		Skillset skillset = new SkillSet(listOfSkills.get(randomNum));	
		return skillset;
	}*/

	public Skill randomAllocationToSet(ArrayList<Skill> listOfSkills) {		
		Random randomiser = new Random();
        int index = randomiser.nextInt(listOfSkills.size());
		Skill skill = listOfSkills.get(index);	
		return skill;
	}	
	
}
