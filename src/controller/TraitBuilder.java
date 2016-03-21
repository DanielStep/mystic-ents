package controller;

import model.traits.*;

public class TraitBuilder {
	
	private TraitSet traitSet;
	
	//In case need to be individually retrieved after initial game setup
	private HealthTrait healthTrait;
	private MoveTrait moveTrait;
	private AttackTrait attackTrait;
	private DamageTrait damageTrait;
	
	public TraitBuilder(){
		
		generateTraitSetfromTraits();
	}
	
	private void generateTraitSetfromTraits(){
		
		healthTrait = new HealthTrait();
		moveTrait = new MoveTrait();
		attackTrait = new AttackTrait();
		damageTrait = new DamageTrait();
		
		traitSet = new TraitSet(healthTrait, moveTrait, attackTrait, damageTrait);	
	}
	
	public TraitSet getTraitSet(){
		return traitSet;
	}
}
