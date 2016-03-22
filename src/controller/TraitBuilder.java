package controller;

import java.util.ArrayList;
import java.util.Random;

import model.traits.*;

public class TraitBuilder {
	
	Random randomNumGen;
	private TraitSet traitSet;
	private ArrayList<Trait> listOfTraits;
	
	//In case need to be individually retrieved after initial game setup
	private HealthTrait healthTrait;
	private MoveTrait moveTrait;
	private AttackTrait attackTrait;
	private DamageTrait damageTrait;
	
	public TraitBuilder(Random randomNumGen){
		
		this.randomNumGen = randomNumGen;
		generateTraitSetfromTraits();
	}
	
	private void generateTraitSetfromTraits(){
		
		healthTrait = new HealthTrait();
		moveTrait = new MoveTrait();
		attackTrait = new AttackTrait();
		damageTrait = new DamageTrait();
		
		allocateTraitValues();
		
		traitSet = new TraitSet(healthTrait, moveTrait, attackTrait, damageTrait);	
	}
	
	private void allocateTraitValues(){
		
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
