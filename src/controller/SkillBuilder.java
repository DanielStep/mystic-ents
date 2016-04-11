package controller;
import java.util.ArrayList;

import java.util.Observable;
import java.util.Observer;

import model.skills.Skill;
import model.skills.SkillRandomizer;
import model.skills.SkillSet;

/**
 * Class instantiates Skills, coordinates their construction by
 * passing them to SkillRandomizer and then aggregating into set
 * @author Daniel
 * @modified, Mark - moved SkillSet to model
 * 
 */
public class SkillBuilder implements Observer {

	private SkillSet skillSet;

	public SkillBuilder() {
		skillSet = new SkillSet();
		observe(skillSet);
		skillSet.init();
	}

	private void generateSkillSetFromSkills(ArrayList<Skill> data) {
		SkillRandomizer skillRandomizer = new SkillRandomizer();
		skillSet.setCurrentSkill(skillRandomizer.randomAllocationToSet(data));
	}
	
	public SkillSet getSkillSet() {
		return skillSet;
	}

	public void observe(Observable o) {
		o.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		ArrayList<Skill> data = ((SkillSet) o).getListOfSkills();
		generateSkillSetFromSkills(data);
	}
}
