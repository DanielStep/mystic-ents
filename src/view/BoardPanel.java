package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import model.GameConfig;
import model.Square;

/**
 * Inner panel for displaying game objects.
 * 
 * @author skh
 *
 */
public class BoardPanel extends JPanel {

	// private PieceActionController pieceActionController;

	public BoardPanel() {
		super();
		this.setPreferredSize(new Dimension(GameConfig.getDefaultWidth(), GameConfig.getDefaultHeight()));
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout(GameConfig.getRowCol(), GameConfig.getRowCol()));
	}

	/**
	 * Draw the squares
	 */
	public void drawBoard(Object[][] board) {		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				SquareView sqr = new SquareView(i, j, (Square) board[i][j]);
				
				/*
				if (board[i][j] != null) {
					sqr.add(new PieceView(Color.RED, true));
				}
				*/
				
				this.add(sqr);
			}
		}
	}
	
	public void refreshBoard(Object[][] boardData) {
		drawBoard(boardData);
	}	

}
