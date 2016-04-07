package model.traits;

public abstract class Trait implements ITraitValueModifiable {

	private int traitValue;
	
	public Trait(int startingValue){
		setTraitValue(startingValue);
	}

	public void modifyValue(int changeBy) {
		setTraitValue(getTraitValue() + changeBy);
	}

	public int getTraitValue() {
		return traitValue;
	}

	public void setTraitValue(int traitValue) {
		this.traitValue = traitValue;
	}
}
