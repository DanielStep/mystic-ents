package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import model.Board;

/**
 * Inner panel for displaying game objects.
 * 
 * @author skh
 *
 */
public class PanelView extends JPanel implements MouseListener {

	private static final int DEFAULT_WIDTH = 700;
	private static final int DEFAULT_HEIGHT = 700;
	private int timerDelay = 1000;
	private final Timer gameTimer;
	private JLabel piece;

	public PanelView() {
		super();

		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		// this.setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout(Board.ROWCOL, Board.ROWCOL));
		addMouseListener(this);

		setupSquares();

		// Updater of the view
		gameTimer = new Timer(timerDelay, timerListener);
		gameTimer.start();
	}

	/**
	 * Draws the square
	 */
	private void setupSquares() {

		for (int i = 0; i < Board.ROWCOL * Board.ROWCOL; i++) {
			JPanel square = new JPanel(new BorderLayout());
			square.setBorder(new LineBorder(Color.BLACK, 1));
			this.add(square);
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public JLabel getPiece() {
		return piece;
	}

	public void setPiece(JLabel piece) {
		this.piece = piece;
	}

	ActionListener timerListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("listener triggered:" + e.getActionCommand());
		}
	};

	public void mousePressed(MouseEvent e) {
		piece = null;
		Component c = findComponentAt(e.getX(), e.getY());

		if (c instanceof JPanel)
			return;

		Point parentLocation = c.getParent().getLocation();
		int xAdjustment = parentLocation.x - e.getX();
		int yAdjustment = parentLocation.y - e.getY();
		piece = (JLabel) c;
		piece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		piece.setSize(piece.getWidth(), piece.getHeight());
		add(piece, JLayeredPane.DRAG_LAYER);
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
