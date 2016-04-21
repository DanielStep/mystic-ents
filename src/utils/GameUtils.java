package utils;

import java.awt.Color;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class GameUtils {
	
	private static GameUtils instance;
	
	private GameUtils(){}
	
	public static synchronized GameUtils getInstance() {
		if (instance == null) {
			instance = new GameUtils();
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
	
	/**
	 * Retrieve all game maps for user to choose
	 * @return a String list of available maps
	 */
	public ArrayList<String> getAllGameMaps() {
		ArrayList<String> maps = new ArrayList<>();
		try {
			File f = new File("./src/model/maps/"); // maps directory

			File[] files = f.listFiles();
			for (File file : files) {
				String fullPath = file.getCanonicalPath();
				String currentFile = fullPath.substring(fullPath.lastIndexOf("\\") + 1);
				currentFile = currentFile.substring(0, currentFile.lastIndexOf("."));
				maps.add(currentFile);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("File map errors: " + e.getMessage());
		}
		return maps;
	}
}
