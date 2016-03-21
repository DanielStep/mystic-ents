package controller.traitGeneration;

import model.traits.*;

public class TraitFactory {
	
	private TraitSet traitSet;
	
	//In case need to be individually retrieved after initial game setup
	private HealthTrait healthTrait;
	private MoveTrait moveTrait;
	private AttackTrait attackTrait;
	private DamageTrait damageTrait;
	
	public TraitFactory(){
		
		generateTraitSetfromTraits();
	}
	
	//Extensible method whereby Trait Generators are instantiated to polymorphically generate trait and pass them to a traitSet.
	private void generateTraitSetfromTraits(){
		
		healthTrait = (HealthTrait) new HealthTraitGenerator().generateTrait();
		moveTrait = (MoveTrait) new MoveTraitGenerator().generateTrait();
		attackTrait = (AttackTrait) new AttackTraitGenerator().generateTrait();
		damageTrait = (DamageTrait) new DamageTraitGenerator().generateTrait();
		
		traitSet = new TraitSet(healthTrait, moveTrait, attackTrait, damageTrait);	
	}
	
	public TraitSet getTraitSet(){
		return traitSet;
	}
}
