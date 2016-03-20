package controller;
import model.Piece;
import model.TraitSet;


public class PieceBuilder {
	
	TraitFactory traitFactory = new TraitFactory();
	SkillFactory skillFactory = new SkillFactory();

	public void buildPiece (Piece newPiece){
		
		TraitSet newTraitSet = null;
		
		
		/* TODO get ArrayList of traits from TraitFactory, iterate and parse to variable by subtype, pass variables as args to newTraitSet
		 * 
		 * TraitSet newTraitSet = new TraitSet(healthTrait, moveTrait, attackTrait, damageTrait);
		 * */
		
		
		
		newPiece.setTraitSet(newTraitSet);
		
		
		
	}
}
