package controller;

import java.util.Random;

import model.Piece;
import model.skills.SkillSet;
import model.traits.*;


public class PieceBuilder {
	
	//Random is seeded by date long here and used throughout the entire game
	Random randomNumGen = new Random(System.currentTimeMillis());
	
	SkillBuilder skillBuilder = new SkillBuilder(randomNumGen);

	public void buildPiece (Piece newPiece){
		
		TraitSet newTraitSet = new TraitBuilder(randomNumGen).getTraitSet();
		
		//TODO*************************
		SkillSet newSkillSet = null;

		newPiece.setTraitSet(newTraitSet);
		newPiece.setSkillSet(newSkillSet);
	}
}
