package controller;

import model.skills.Skill;
import model.traits.AttackTrait;
import model.traits.DamageTrait;
import model.traits.HealthTrait;
import model.traits.RangeTrait;
import model.traits.Trait;



public class TraitFactory extends AbstractFactory{
	
	final int INITIALTRAITVALUE;
	

	public TraitFactory(int initialValue) {
		this.INITIALTRAITVALUE = initialValue;
	}
	
	@Override
	public Trait makeTrait(String traitType){
		
		if(traitType == null){
			return null;
		}
		
		if(traitType.equalsIgnoreCase("HEALTH")){
			return new HealthTrait(INITIALTRAITVALUE);
		}else if(traitType.equalsIgnoreCase("RANGE")){
			return new RangeTrait(INITIALTRAITVALUE);
		}else if(traitType.equalsIgnoreCase("DAMAGE")){
			return new DamageTrait(INITIALTRAITVALUE);
		}else if(traitType.equalsIgnoreCase("ATTACK")){
			return new AttackTrait(INITIALTRAITVALUE);
		}
		
		return null;
	}
	
	@Override
	Skill makeSkill(String skillType) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
