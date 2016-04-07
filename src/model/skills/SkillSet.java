package model.skills;

import java.util.ArrayList;
import java.util.Observable;

public class SkillSet extends Observable {
	
	private ArrayList<Skill> listOfSkills = new ArrayList<Skill>();

	private Skill currentSkill ;
	
	public void init() {
		buildSkillset();
	}
	
	private void buildSkillset() {
		
		HealSkill healSkill = new HealSkill();
		BuildSkill buildSkill = new BuildSkill();
		RangeSkill speedSkill = new RangeSkill();
		AttackSkill attackSkill = new AttackSkill();
		
		listOfSkills.add(healSkill);
		listOfSkills.add(buildSkill);
		listOfSkills.add(speedSkill);
		listOfSkills.add(attackSkill);
		
		//Passes the ArrayList to SkillBuilder
		setChanged();
	    notifyObservers();
	}

	public Skill getCurrentSkill() {
		return currentSkill;
	}
	
	public ArrayList<Skill> getListOfSkills() {
		return listOfSkills;
	}

	public void setListOfSkills(ArrayList<Skill> listOfSkills) {
		this.listOfSkills = listOfSkills;
	}
	
	public void setCurrentSkill(Skill newSkill) {
		this.currentSkill = newSkill;
	}

}
