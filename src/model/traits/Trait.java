package model.traits;

public abstract class Trait implements ITraitValueModifiable {

	private int traitValue;
	
	public Trait(int startingValue){
		traitValue = startingValue;
	}


	public void modifyValue(int changeBy) {
		
		traitValue += changeBy;
	}
}
