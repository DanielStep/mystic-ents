package controller;
import java.util.Random;

import model.Piece;
import model.Team;
import model.skills.SkillSet;
import model.traits.*;

/**
 * Class is passed instantiated but empty Piece and builds it with Traits, Skill and Team
 * @author Daniel
 *
 */
public class PieceBuilder {
	
	//Random is seeded by date long here and used throughout the piece gen process
	Random randomNumGen = new Random(System.currentTimeMillis());
	
	//SkillBuilder skillBuilder = new SkillBuilder(randomNumGen);

	/**
	 * Method is passed empty Piece and team num,
	 * and coordinates the aggregation of skill and trait sets into the piece,
	 * and assigns team to the piece
	 * @param newPiece
	 * @param team
	 */
	public void buildPiece (Piece newPiece, Enum<Team> team){
		
		TraitSet newTraitSet = new TraitBuilder(randomNumGen).getTraitSet();
		SkillSet newSkillSet = new SkillBuilder(randomNumGen).getSkillSet();

		newPiece.setTraitSet(newTraitSet);
		newPiece.setSkillSet(newSkillSet);
		newPiece.setTeam(team);

	}
}
