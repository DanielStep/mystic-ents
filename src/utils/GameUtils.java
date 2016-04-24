package utils;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	/**
	 * Save the current game state to file
	 * @param gameState
	 */
	public void saveGame(Object gameState){
	    try {
	    	FileOutputStream fileOut = new FileOutputStream(GameConfig.SAVE_GAME_FILE);
	        ObjectOutputStream oos = new ObjectOutputStream(fileOut);
	        oos.writeObject(gameState);
	        oos.close();
	        fileOut.close();
	    } catch(Exception e) {
	        System.out.println("Error saving game: " + e.getMessage());
	    }
	}
	
	/**
	 * Load the previous game state from file 
	 * @return gameState as an Object
	 */
	public Object loadGame(){
		Object gameSate = null;
		try {
			FileInputStream fileIn = new FileInputStream(GameConfig.SAVE_GAME_FILE);
			ObjectInputStream ois = new ObjectInputStream(fileIn);
			gameSate = ois.readObject();
			ois.close();
			fileIn.close();
		} catch (Exception e) {
			System.out.println("Error loading game: " + e.getMessage());
		}
		return gameSate;
	}
}
