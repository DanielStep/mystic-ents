package controller;

public class FactoryProducer {
	public static AbstractFactory getFactory(String choice){
		
		if(choice.equalsIgnoreCase("TRAIT")){
			return new TraitFactory(1);
		}else if(choice.equalsIgnoreCase("SKILL")){
			
			return new SkillFactory();
		}
		
		return null;
	}  

}
