package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import model.Board;
import model.GameConfig;

/**
 * Inner panel for displaying game objects.
 * 
 * @author skh
 *
 */
public class GamePanel extends JPanel {

	private static final int DEFAULT_WIDTH = 700;
	private static final int DEFAULT_HEIGHT = 700;
	private int timerDelay = 1000;
	private final Timer gameTimer;

	public GamePanel() {
		super();
		
		int panelSize = GameConfig.getROW_COL(); 

		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		// this.setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout(panelSize, panelSize));

		for (int i = 0; i < panelSize * panelSize; i++) {
			JPanel square = new JPanel(new BorderLayout());
			square.setBorder(new LineBorder(Color.BLACK, 1));
			this.add(square);
		}

		// Updater of the view
		gameTimer = new Timer(timerDelay, timerListener);
		gameTimer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	ActionListener timerListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println("listener triggered");
		}
	};

}
