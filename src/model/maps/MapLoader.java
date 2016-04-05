package model.maps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.GameConfig;

public class MapLoader {
	
	ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
	
	public ArrayList<ArrayList<Integer>> getMapData() throws IOException {
		
		try {

			int i = 0;
			
			// Create our bufferedreader to read the file
			BufferedReader reader = new BufferedReader(new FileReader(GameConfig.getMapTextfile()));
			
			// Line to hold the line read from file
			String line = "";
			
			// Loop through the file reading in lines and storing in "line". Do this until readLine returns null (end of file)
			while ((line = reader.readLine()) != null) {
				String[] numbers = line.split(" ");

				//Create the 2d ArrayList
				map.add(new ArrayList<Integer>());
		        ArrayList<Integer> inner = map.get(i);
				
		        //System.out.println("setROW_COL: " + i); 
				for(int j = 0; j < numbers.length; j++) {
					inner.add(Integer.parseInt(numbers[j]));
				}
				i++;
			}

			//Set the Config parameter for board gridsize based on map
			GameConfig.setROW_COL(i);
			reader.close();
		}
		catch (Exception ex) { 
			System.out.println("getMapData() Exception: " + ex.getMessage()); 
		}
		
		return map;
		
	}
	
}
