package model.skills;
import java.util.ArrayList;
import java.util.Random;
/**
 * Class is passed list of all skills and randomly assigns one to a skill set
 * @author Daniel
 *
 */
public class SkillRandomizer {
	
	/*public Skillset randomAllocationToSet(ArrayList<Skill> listOfSkills, Random randomNumGen){	
		int randomNum = randomNumGen.ints(0, listOfSkills.size()).limit(1).findFirst().getAsInt();
		Skillset skillset = new SkillSet(listOfSkills.get(randomNum));	
		return skillset;
	}*/
	
	/**
	 * Method is passed list of all skills and randomly assigns one to a skill set which
	 * it returns
	 * TODO: Refactor for Builder Pattern, move SkillSet instantiation to SkillBuilders
	 * @param listOfSkills
	 * @param randomNumGen
	 * @return SkillSet
	 */
	public Skill randomAllocationToSet(ArrayList<Skill> listOfSkills) {		
		Random randomiser = new Random();
        int index = randomiser.nextInt(listOfSkills.size());
		Skill skill = listOfSkills.get(index);	
		return skill;
	}	
	
}
