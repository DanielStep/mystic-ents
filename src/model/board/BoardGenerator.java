package model.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import model.piece.Piece;
import utils.MapLoader;

/**
 * Factory method to Generate board data for view to draw
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
	
	/**
	 * Iterates over the loaded map data and assigns a Square Object
	 * corresponding to the value of the data
	 * 
	 * @author Mark
	 *
	 * @param piecesList
	 * sent from the model after piece creation
	 * 
	 * @return
	 * Square[][] - the 2D game board objects
	 * 
	 */	
	
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
	
	/**
	 * Processes map data based on game rules
	 * Returns the Square object
	 * 
	 * @author Mark
	 *
	 * @param col
	 * the x value of the cell
	 * @param row
	 * the y value of the cell
	 * @param cell
	 * the map data value of the cell
	 * 
	 * @return
	 * Square assigned properties based on map data rules
	 */		
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

}
