package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import controller.PieceActionController;

import model.GameConfig;
import model.Square;

/**
 * Inner panel for displaying game objects.
 * 
 * @author skh
 *
 */
public class BoardPanel extends JPanel {
	
	PieceActionController pac;

	public BoardPanel() {
		super();
		this.setPreferredSize(new Dimension(GameConfig.getDefaultWidth(), GameConfig.getDefaultHeight()));
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout(GameConfig.getRowCol(), GameConfig.getRowCol()));
	}

	public PieceActionController getPac() {
		return pac;
	}

	public void setPac(PieceActionController pac) {
		this.pac = pac;
	}

	/**
	 * Draw the squares
	 */
	public void drawBoard(Object[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				SquareView sqr = new SquareView(pac, (Square) board[i][j]);
				this.add(sqr);
			}
		}
	}
	
	public void refreshBoard(Object[][] boardData) {
		this.removeAll();
		drawBoard(boardData);
		this.revalidate();
	}	

}
