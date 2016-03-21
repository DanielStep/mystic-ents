package controller.traitGeneration;

import java.util.ArrayList;

import model.Trait;

public class TraitFactory {

	private ArrayList<Trait> listOfTraits;
	private ArrayList<ITraitGenerator> traitsToGenerate;
	
	public ArrayList<Trait> getListOfTraits(){
		return listOfTraits;
	}
	
	public void generateTraits(){
		
		traitsToGenerate = new ArrayList<ITraitGenerator>();
		traitsToGenerate.add(new HealthTraitGenerator());
	}
}
