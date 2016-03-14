package view;

import javax.swing.JFrame;

/**
 * Graphical representation of the board. Responsible for managing GUI
 * 
 * @author skh
 *
 */
public class BoardView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Draws the shape. */
	private PanelView gamePanel;

	public BoardView() {
		super("OurGame");
		gamePanel = new PanelView();
		start();
	}

	public void start() {
		setResizable(false);
		setVisible(true);
		setupGUI();
	}

	private void setupGUI() {
		// Good practice - don't have modify JFrame
		/*
		 * JPanel rootPanel = new JPanel(); rootPanel.add(gamePanel);
		 * add(rootPanel);
		 */
		add(gamePanel);

		// resize the panel so objects fit in.
		pack();
	}

}
