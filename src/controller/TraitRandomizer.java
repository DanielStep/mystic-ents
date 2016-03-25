package controller;

import java.util.ArrayList;
import java.util.Random;

import model.traits.*;

public class TraitRandomizer {
	
	final int VALUESDISTRIBUTED = 6;
	final int VALUEINCREMENT = 1;

	public void generateRandomTraitValues(ArrayList<Trait> listofTraits, Random randomNumGen){
		
		int randomNum;
		
		for (int i = 0; i < VALUESDISTRIBUTED; i++) {
			
			randomNum = randomNumGen.ints(0, listofTraits.size()).limit(1).findFirst().getAsInt();
			listofTraits.get(randomNum).modifyValue(VALUEINCREMENT);
		}
	}
	
}
