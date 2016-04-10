package model.skills;
/**
 * Abstract class from which all Skill inherit
 * @author 
 *
 */
public abstract class Skill{
	private String name;
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
