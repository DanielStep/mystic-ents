package model;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import model.maps.MapLoader;

/**
 * Generates board board data for view to draw
 * 
 * @author ms
 *
 */

public class BoardGenerator {
	
	private ArrayList<Piece> gamePieces;
	private int[][] map;
	
	public BoardGenerator() {		
		MapLoader mapData = new MapLoader();
		try {
			map = mapData.getMapData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public Square[][] generateStartBoard(ArrayList<Piece> piecesList) {
		
		gamePieces = piecesList;		
		
		Square[][] boardData = new Square[GameConfig.getRowCol()][GameConfig.getRowCol()];		
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				
				boardData[i][j] = null;// blank
				Square gsqr = new Square(i, j);
	
				//WALLS
				if (map[i][j] == 1) {
					gsqr.setAccessible(false);
				}
				
				//TEAMS
				if (map[i][j] == 2) { 
					gsqr.setOccupant(findAvailablePiece(gamePieces, "RED"));
				}
				if (map[i][j] == 3) {
					gsqr.setOccupant(findAvailablePiece(gamePieces, "BLUE"));
				}

				//TOWERS
				if (map[i][j] == 8 || map[i][j] == 9) { 
					gsqr.setTeamTower(true);
				}
				
				boardData[i][j] = gsqr;
			}
		}		
		return boardData;
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
	
	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}


}
