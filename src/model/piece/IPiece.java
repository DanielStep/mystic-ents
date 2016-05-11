package model.piece;

import model.board.Square;
import model.skills.SkillSet;
import model.traits.TraitSet;

public interface IPiece {


	public Square getParentSquare();
	
	public void setParentSquare(Square parentSquare);
	
	public String getIcon();

	public void setIcon(String icon);

	public SkillSet getSkillSet();

	public void setSkillSet(SkillSet skillSet);

	public TraitSet getTraitSet();

	public void setTraitSet(TraitSet traitSet);

	public Enum<Team> getTeam();

	public void setTeam(Enum<Team> team);

	public void attackOut(Piece piece);

	public Boolean getInPlay();

	public void setInPlay(Boolean inPlay);

	public Boolean getIsUsurper();

	public void setIsUsurper(Boolean isUsurper);

}
