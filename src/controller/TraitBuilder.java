package controller;

import java.util.ArrayList;
import java.util.Random;

import model.traits.*;

public class TraitBuilder {
	
	private Random randomNumGen;
	private TraitSet traitSet;
	
	public TraitBuilder(Random randomNumGen){
		
		this.randomNumGen = randomNumGen;
		generateTraitSetfromTraits();
	}
	
	private void generateTraitSetfromTraits(){
		
		HealthTrait healthTrait = new HealthTrait();
		MoveTrait moveTrait = new MoveTrait();
		AttackTrait attackTrait = new AttackTrait();
		DamageTrait damageTrait = new DamageTrait();
		
		allocateTraitValues(healthTrait, damageTrait, moveTrait);
		
		traitSet = new TraitSet(healthTrait, moveTrait, attackTrait, damageTrait);	
	}
	
	private void allocateTraitValues(HealthTrait healthTrait, DamageTrait damageTrait, MoveTrait moveTrait){
		
		ArrayList<Trait> listOfTraits = new ArrayList<Trait>();
		
		listOfTraits.add(damageTrait);
		listOfTraits.add(moveTrait);
		listOfTraits.add(healthTrait);
		
		TraitRandomizer traitRandomizer = new TraitRandomizer();
		traitRandomizer.generateRandomTraitValue(listOfTraits, randomNumGen);
	}
	
	
	public TraitSet getTraitSet(){
		return traitSet;
	}
}
