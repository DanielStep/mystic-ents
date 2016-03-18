package view;

import javax.swing.JFrame;

import controller.PieceActionController;
import model.Board;

/**
 * Outer container of the board.
 * 
 * @author skh
 *
 */
public class BoardFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	/** Draws the shape. */
	private BoardPanel gamePanel;

	public BoardFrame() {
		super("OurGame");
		gamePanel = new BoardPanel();
		start();
	}

	public void start() {
		setResizable(false);
		setVisible(true);
		add(gamePanel);

		// resize the panel so objects fit in.
		pack();
	}

}
