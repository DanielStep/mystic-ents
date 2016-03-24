package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

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

	public BoardFrame() {
		super("OurGame");
		buildFrame();
		buildUI();
	}

	private void buildFrame() {		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = this.getContentPane();
        c.setBackground(Color.YELLOW);
        // adjust size using Dimension.
        c.setPreferredSize(new Dimension(GameConfig.getDefaultWidth(), GameConfig.getDefaultHeight()));
        // resize the panel so objects fit in.
        //pack();
        setResizable(false);
        setVisible(true);
	}
	
	public void buildUI() {
		gamePanel = new BoardPanel();
		this.add(gamePanel);
	}
	
	public BoardPanel getBoardPanel() {
		return gamePanel;
	}
	
	public void refreshBoard(Object[][] data) {
		//gamePanel.refreshBoard(data);
	}

}
