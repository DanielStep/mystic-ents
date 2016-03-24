package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * A component of ControlFrame
 * @author Phan Vo
 *
 */
public class EndTurnPanel extends JPanel{
	private JButton btnEndTurn;
	
	public EndTurnPanel() {
		// TODO Auto-generated constructor stub
		super(true);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		btnEndTurn = new JButton("End turn");
		btnEndTurn.setPreferredSize(new Dimension(158, 50));
		btnEndTurn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO call the end-turn method from the controller
				System.out.println("-------manually/auto click end turn btn");
			}
		});
		
		this.add(btnEndTurn);
	}
}
