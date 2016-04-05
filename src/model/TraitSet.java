package model;

import java.util.ArrayList;
import java.util.HashMap;

public class TraitSet {
	
	//TODO Consider how to make more extensible, ie adding another trait subtype without modifying structure (open closed) but retaining accessability
	
	private Trait healthTrait;
	private Trait moveTrait;
	private Trait attackTrait;
	private Trait damageTrait;

	
	public TraitSet (Trait healthTrait, Trait moveTrait, Trait attackTrait, Trait damageTrait){
		
		this.healthTrait = healthTrait;
		this.moveTrait = moveTrait;
		this.attackTrait = attackTrait;
		this.damageTrait = damageTrait;
		
	}
}
