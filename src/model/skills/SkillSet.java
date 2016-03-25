package model.skills;

import java.util.ArrayList;

public class SkillSet {
	
	private Skill currentSkill;
	
	public SkillSet(Skill newSkill){
		
		this.currentSkill = newSkill;
		
	}

	public Skill getCurrentSkill() {
		return currentSkill;
	}

}
