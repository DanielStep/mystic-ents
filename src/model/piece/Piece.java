package model.piece;

import model.skills.SkillSet;
import model.traits.TraitSet;

public abstract class Piece implements IAttack {

	protected TraitSet traitSet;
	protected SkillSet skillSet;
	
	protected Boolean isUsurper = false;
	protected Boolean inPlay = false;
	protected Boolean inMove = false;

	protected int id;
	/** Current x coordinate **/
	protected int cX;
	/** Current y coordinate **/
	protected int cY;	

	// Team color
	protected Enum<Team> team;
	
	public SkillSet getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(SkillSet skillSet) {
		this.skillSet = skillSet;
	}

	public TraitSet getTraitSet() {
		return traitSet;
	}

	public void setTraitSet(TraitSet traitSet) {
		this.traitSet = traitSet;
	}

	public int getcX() {
		return cX;
	}

	public void setcX(int cX) {
		this.cX = cX;
	}

	public int getcY() {
		return cY;
	}

	public void setcY(int cY) {
		this.cY = cY;
	}

	public Enum<Team> getTeam() {
		return team;
	}

	public void setTeam(Enum<Team> team) {
		this.team = team;
	}	

	public void attackIn(int att) {
		// TODO Auto-generated method stub

	}

	public void attackOut(Piece piece) {
		
		System.out.println(this.getTraitSet().getDamageTrait().getTraitValue() + " : Attack on : " + piece.getTraitSet().getHealthTrait().getTraitValue());
		
		// TODO Auto-generated method stub

	}
	
	public Boolean getInMove() {
		return inMove;
	}

	public void setInMove(Boolean inMove) {
		this.inMove = inMove;
	}

	public Boolean getInPlay() {
		return inPlay;
	}

	public void setInPlay(Boolean inPlay) {
		this.inPlay = inPlay;
	}

	public Boolean getIsUsurper() {
		return isUsurper;
	}

	public void setIsUsurper(Boolean isUsurper) {
		this.isUsurper = isUsurper;
	}

}
