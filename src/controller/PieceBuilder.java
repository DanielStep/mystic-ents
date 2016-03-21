package controller;

import model.Piece;
import model.traits.*;


public class PieceBuilder {
	
	SkillBuilder skillBuilder = new SkillBuilder();

	public void buildPiece (Piece newPiece){
		
		TraitSet newTraitSet = new TraitBuilder().getTraitSet();

		newPiece.setTraitSet(newTraitSet);
	}
}
