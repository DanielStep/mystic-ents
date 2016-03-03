package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Inner panel for displaying game objects.
 * 
 * @author skh
 *
 */
public class GamePanel extends JPanel {

	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 500;
	private int timerDelay = 1000;
	private final Timer gameTimer;

	public GamePanel() {
		super();

		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		// this.setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.setVisible(true);
		this.setBackground(Color.BLACK);

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
			System.out.println("listener triggered");
		}
	};

}
