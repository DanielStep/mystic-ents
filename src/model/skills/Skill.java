package model.skills;

import java.io.Serializable;

/**
 * Abstract class from which all Skill inherit
 * @author 
 *
 */
public abstract class Skill implements Serializable {
	private String name;
	private String icon;
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setIcon(String icon){
		this.icon = icon;
	}
	
	public String getIcon(){
		return this.icon;
	}	
}
