package model;

import java.awt.Color;
import java.lang.reflect.Field;

public class Game {
	
	private static Game instance;
	
	private Game(){}
	
	public static synchronized Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}
	
	/**
	* Converts a given string into a color.
	* 
	* @param value
	* 	the team name corresponding to a color.	
	* @param dft
	* 	is sent as a fallback (default) if the parsing fails.
	* @return the color.
	*/
	public Color stringToColor(final String value, Color dft) {
		//null value is handled by returning default; 
		if (value == null) {
			return dft;
		}
		try {
			// try to get a color by name using reflection
			final Field f = Color.class.getField(value);
			return (Color) f.get(null);
		} catch (Exception ce) {
			// if we can't get any color return default
			return dft;
		}
	}
}