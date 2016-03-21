package model.traits;

public abstract class Trait implements ITraitValueModifiable {

	private int traitValue;
	
	public void modifyValue() {
		//Override in subclasses
	}
}
