package controller;

import java.util.ArrayList;
import java.util.Random;

import model.traits.*;

public class TraitBuilder {

	private Random randomNumGen;
	private TraitSet traitSet;

	/**
	 * Trait set is created on instantiation of Trait Builder
	 * 
	 * @param randomNumGen
	 *            - Seeded Random generator
	 */

	public TraitBuilder(Random randomNumGen) {

		this.randomNumGen = randomNumGen;
		generateTraitSetfromTraits();
	}

	/**
	 * All traits instantiated here, references passed to allocateTraitValues
	 * then aggregated into a trait set
	 */

	private void generateTraitSetfromTraits() {

		HealthTrait healthTrait = new HealthTrait(1);
		MoveTrait moveTrait = new MoveTrait(1);
		AttackTrait attackTrait = new AttackTrait(1);
		DamageTrait damageTrait = new DamageTrait(1);

		allocateTraitValues(healthTrait, damageTrait, moveTrait);

		traitSet = new TraitSet(healthTrait, moveTrait, attackTrait, damageTrait);
	}

	/**
	 * Trait references with randomizable values packaged into ArrayList, passed
	 * to traitRandomizer along with random number generate
	 * 
	 * @param healthTrait
	 * @param damageTrait
	 * @param moveTrait
	 */
	private void allocateTraitValues(HealthTrait healthTrait, DamageTrait damageTrait, MoveTrait moveTrait) {

		ArrayList<Trait> listOfTraits = new ArrayList<Trait>();

		listOfTraits.add(damageTrait);
		listOfTraits.add(moveTrait);
		listOfTraits.add(healthTrait);

		TraitRandomizer traitRandomizer = new TraitRandomizer();
		traitRandomizer.generateRandomTraitValues(listOfTraits, randomNumGen);
	}
	
	
	
	

	public TraitSet getTraitSet() {
		return traitSet;
	}
}
