package model;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import model.maps.MapLoader;

/**
 * Generates board board data for view to draw
 * First, uses BufferedReader in MapLoader class to load map data.
 * When Board model is ready, uses Iterator to generateStartBoard
 * from map data.
 * A corresponding Square Object is assigned to the ArrayList Model
 * Its properties are defined according to model value
 *  
 * @author Mark
 *
 */

public class BoardGenerator {
	
	private ArrayList<Piece> gamePieces;
	private ArrayList<ArrayList<Integer>> map;
	
	public void loadMapData() {		
		MapLoader mapData = new MapLoader();
		try {
			map = mapData.getMapData();
			System.out.println("Loading Map Size: " + map.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public Square[][] generateStartBoard(ArrayList<Piece> piecesList) {
		
		//Shuffle the list for different start location
		gamePieces = piecesList;
		List<Piece> list = piecesList;
		Collections.shuffle(list);

	    int col = 0;
	    int row = 1;
	    int size = map.size();
	    
	    Square[][] boardData = new Square[map.size()][map.size()];
		Iterator<ArrayList<Integer>> mapIterator = map.iterator();

	    while (mapIterator.hasNext()) {

	        ArrayList<Integer> line = mapIterator.next();

	        if (row > line.size()) {
	            break;
	        }
	        
	        Iterator<Integer> val = line.iterator();
	        
	        int index = 0;

	        Integer cell = 0;
	        while (index != row) {
	        	cell = val.next();
	        	index++;
	        }
	        
	        boardData[col][row-1] = null;// blank
	        Square gsqr = processMapData(col, row-1, cell);
			//Assign the Square to the board
			boardData[col][row-1] = gsqr;

			//Reset the iterator values
	        index = 0;
	        col++;
	        
	        if (col == size) {
	        	//reset the iterator
	        	mapIterator = map.iterator();  
	            row++;
	            col = 0;
	        }
	        
	    }

		return boardData;
	}
	
	private Square processMapData(int col, int row, int cell) {
		
		Square gsqr = new Square();
		
		//WALLS
		if (cell == 1) {
			gsqr.setAccessible(false);
		}
		
		//TEAMS
		if (cell == 2) { 
			gsqr.setOccupant(findAvailablePiece(gamePieces, "RED"));
		}
		if (cell == 3) {
			gsqr.setOccupant(findAvailablePiece(gamePieces, "BLUE"));
		}

		//TOWERS
		if (cell == 8 || cell == 9) {
			gsqr.setTeamTower(true);
		}
		
		int[] ids = {col, row};		
		gsqr.setID(ids);
		
		return gsqr;
		
	}

	private Piece findAvailablePiece(ArrayList<Piece> gamePieces, String team) {
		for (Piece i : gamePieces) {
			if (i.getInPlay() == false) {
				if (i.getTeam().toString() == team) {
					i.setInPlay(true);
					return i;
				}
			}
		}
		return null;
	}
	
	/*public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}*/


}
