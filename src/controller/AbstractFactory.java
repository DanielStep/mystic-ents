package controller;

import model.skills.Skill;
import model.traits.Trait;

public abstract class AbstractFactory {
	
	abstract Trait makeTrait(String traitType);
	abstract Skill makeSkill(String skillType);
	

}
