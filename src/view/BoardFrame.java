package view;

import javax.swing.JFrame;

import controller.PieceActionController;

/**
 * Outer container of the board.
 * 
 * @author skh
 *
 */
public class OuterFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	/** Draws the shape. */
	private BoardPanel gamePanel;

	public OuterFrame(PieceActionController pieceActionController, Object[][] boardData) {
		super("OurGame");
		gamePanel = new BoardPanel(pieceActionController, boardData);
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
