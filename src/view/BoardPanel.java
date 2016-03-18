package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
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

/**
 * Inner panel for displaying game objects.
 * 
 * @author skh
 *
 */
public class BoardPanel extends JPanel implements Observer, MouseListener {

	private static final int DEFAULT_WIDTH = 700;
	private static final int DEFAULT_HEIGHT = 700;
	// private PieceActionController pieceActionController;
	private int timerDelay = 1000;
	private final Timer gameTimer;

	public BoardPanel(PieceActionController pieceActionController, Object[][] boardData) {
		super();

		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout(Board.ROW_COL, Board.ROW_COL));
		addMouseListener(this);

		setupSquares(boardData);

		// Updater of the view
		gameTimer = new Timer(timerDelay, timerListener);
		// gameTimer.start();
	}

	/**
	 * Draw the squares
	 */
	private void setupSquares(Object[][] board) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				SquareView sqr = new SquareView();
				if (board[i][j] != null) {					
					sqr.add(new PieceView(Color.RED, true));
				}
				this.add(sqr);
			}
		}
	}

	ActionListener timerListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("listener triggered:" + e.getActionCommand());
		}
	};

	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
