package model;

import model.skills.Skill;
import model.skills.SkillSet;
import model.traits.TraitSet;

public abstract class Piece implements IAttack {

	private TraitSet traitSet;
	private SkillSet skillSet;
	
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

	protected int id;
	/** Current x coordinate **/
	protected int cX;
	/** Current x coordinate **/
	protected int cY;	

	// Team color
	protected Enum<Team> team;

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
		// TODO Auto-generated method stub

	}

}
