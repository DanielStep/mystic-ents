package model.maps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.GameConfig;

public class MapLoader {

	public int[][] getMapData() throws IOException {
	
		int[][] map = new int[GameConfig.getRowCol()][GameConfig.getRowCol()];
		int i = 0;
		try {
			// Create our bufferedreader to read the file
			BufferedReader reader = new BufferedReader(new FileReader(GameConfig.getMapTextfile()));
			
			// Line to hold the line read from file
			String line = "";
			
			// Loop through the file reading in lines and storing in "line". Do this until readLine returns null (end of file)
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				String[] numbers = line.split(" "); 
				for(int j = 0; j < numbers.length; j++) {
					map[i][j] = Integer.parseInt(numbers[j]);
				}
				i++;
			}
			reader.close();
		}
		catch (Exception ex) { 
			System.out.println("Exception: " + ex.getMessage()); 
		}
		
		return map;
	}
	
}
