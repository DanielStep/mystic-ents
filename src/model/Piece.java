package model;

public abstract class Piece implements Skill, IAttack {

	private TraitSet traitSet;
	
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
	protected Team team;

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

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public void skillOut() {
		// TODO Auto-generated method stub

	}

	public void attackIn(int att) {
		// TODO Auto-generated method stub

	}

	public void attackOut(Piece piece) {
		// TODO Auto-generated method stub

	}

}
