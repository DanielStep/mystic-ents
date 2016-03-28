package controller;

import java.util.ArrayList;
import java.util.Random;

import model.traits.*;

public class TraitBuilder {

	private Random randomNumGen;
	private TraitSet traitSet;
	final int INITIALTRAITVALUE = 1;

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

		HealthTrait healthTrait = new HealthTrait(INITIALTRAITVALUE);
		RangeTrait rangeTrait = new RangeTrait(INITIALTRAITVALUE);
		AttackTrait attackTrait = new AttackTrait(INITIALTRAITVALUE);
		DamageTrait damageTrait = new DamageTrait(INITIALTRAITVALUE);

		allocateTraitValues(healthTrait, damageTrait, rangeTrait);

		traitSet = new TraitSet(healthTrait, rangeTrait, attackTrait, damageTrait);
	}

	/**
	 * Trait references with randomizable values packaged into ArrayList, passed
	 * to traitRandomizer along with random number generate
	 * 
	 * @param healthTrait
	 * @param damageTrait
	 * @param rangeTrait
	 */
	private void allocateTraitValues(HealthTrait healthTrait, DamageTrait damageTrait, RangeTrait rangeTrait) {

		ArrayList<Trait> listOfTraits = new ArrayList<Trait>();

		listOfTraits.add(damageTrait);
		listOfTraits.add(rangeTrait);
		listOfTraits.add(healthTrait);

		TraitRandomizer traitRandomizer = new TraitRandomizer();
		traitRandomizer.generateRandomTraitValues(listOfTraits, randomNumGen);
	}
	
	
	
	

	public TraitSet getTraitSet() {
		return traitSet;
	}
}
