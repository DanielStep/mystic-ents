package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.game.GameTurn;

/**
 * A component of ControlPanel: manually/automatically end the current turn
 * 
 * @author Phan Vo
 *
 */
public class EndTurnPanel extends JPanel {
	private JButton btnEndTurn;
	private GameTurn gameTurn;

	public EndTurnPanel() {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		btnEndTurn = new JButton("End turn");
		btnEndTurn.setPreferredSize(new Dimension(158, 50));
		btnEndTurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO call the end-turn method from the controller
				executeEndTurn();
			}
		});

		this.add(btnEndTurn);
	}

	public void executeEndTurn() {
		System.out.println("Starting turn: " + gameTurn.getCount());
		gameTurn.setGameTimer(0);
	}

	public void setGameTurn(GameTurn gameTurn) {
		this.gameTurn = gameTurn;
	}
}
