package model.piece;

import java.io.Serializable;

import model.board.Square;
import model.skills.SkillSet;
import model.traits.TraitSet;

public abstract class Piece implements IAttack, Serializable {

	protected TraitSet traitSet;
	protected SkillSet skillSet;

	protected String pieceIcon;

	protected Boolean isUsurper = false;
	protected Boolean inPlay = true;
	protected Boolean inMove = false;

	//protected int id;
	/** Current x coordinate **/
	//protected int cX;
	/** Current y coordinate **/
	//protected int cY;

	// Team color
	protected Enum<Team> team;
	
	//Keep reference to parent Square for AI 
	protected Square parentSquare;

	public Piece() {
	}

	public Piece(Piece other) {
		this.traitSet = other.traitSet;
		this.skillSet = other.skillSet;
		this.pieceIcon = other.pieceIcon;
		this.isUsurper = other.isUsurper;
		this.inMove = other.inMove;
		//this.id = other.id;
		//this.cX = other.cX;
		//this.cY = other.cY;
		this.team = other.team;
	}

	public Square getParentSquare() {
		return parentSquare;
	}
	public void setParentSquare(Square parentSquare) {
		this.parentSquare = parentSquare;
	}
	public String getIcon() {
		return pieceIcon;
	}

	public void setIcon(String icon) {
		this.pieceIcon = icon;
	}

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

	/*public int getcX() {
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
	}*/

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

		int damageValue = this.getTraitSet().getDamageTrait().getTraitValue();
		piece.getTraitSet().getHealthTrait().modifyValue(-damageValue);

		System.out.println(this.getTraitSet().getDamageTrait().getTraitValue() + " : Attack on : "
				+ piece.getTraitSet().getHealthTrait().getTraitValue());

	}

	/*public Boolean getInMove() {
		return inMove;
	}

	public void setInMove(Boolean inMove) {
		this.inMove = inMove;
	}*/

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
