package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.Timer;

import controller.PieceActionController;
import model.Board;
import model.GameConfig;

/**
 * Inner panel for displaying game objects.
 * 
 * @author skh
 *
 */
public class BoardPanel extends JPanel {

	// private PieceActionController pieceActionController;

	public BoardPanel(PieceActionController pieceActionController, Object[][] boardData) {
		super();

		this.setPreferredSize(new Dimension(GameConfig.getDefaultWidth(), GameConfig.getDefaultHeight()));
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout(GameConfig.getRowCol(), GameConfig.getRowCol()));

		//addMouseListener(pieceActionController);
		drawBoard(boardData);
		
	}

	/**
	 * Draw the squares
	 */
	private void drawBoard(Object[][] board) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				SquareView sqr = new SquareView(i, j);
				
				if (board[i][j] != null) {
					sqr.add(new PieceView(Color.RED, true));
				}
				
				this.add(sqr);
			}
		}
	}
	
	public void refreshBoard(Object[][] boardData) {
		drawBoard(boardData);
	}	

}
