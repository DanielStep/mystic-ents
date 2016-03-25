package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

import controller.PieceActionController;
import model.Board;
import model.GameConfig;

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
	private ControlPanel controlPanel;

	public BoardFrame() {
		super("OurGame");
		buildFrame();
		buildUI();
	}

	/**
	 * Adding and configuring properties of the frame
	 */
	private void buildFrame() {
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = this.getContentPane();
		c.setBackground(Color.YELLOW);

		int totalGameWidth = GameConfig.getDefaultWidth() + GameConfig.getDefaultControlPanelWidth();

		// adjust size using Dimension.
		c.setPreferredSize(new Dimension(totalGameWidth, GameConfig.getDefaultHeight()));
		// resize the panel so objects fit in.
		// pack();
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * Creating the board panel and control panel
	 */
	public void buildUI() {
		gamePanel = new BoardPanel();
		this.add(gamePanel, BorderLayout.CENTER);

		controlPanel = new ControlPanel();
		this.add(controlPanel, BorderLayout.EAST);
	}

	public BoardPanel getBoardPanel() {
		return gamePanel;
	}

	public void refreshBoard(Object[][] data) {
		// gamePanel.refreshBoard(data);
	}

}
