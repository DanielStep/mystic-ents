package controller;

import controller.traitGeneration.TraitFactory;
import model.Piece;
import model.traits.*;


public class PieceBuilder {
	
	SkillFactory skillFactory = new SkillFactory();

	public void buildPiece (Piece newPiece){
		
		TraitSet newTraitSet = new TraitFactory().getTraitSet();

		newPiece.setTraitSet(newTraitSet);
	}
}
