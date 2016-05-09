package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.piece.Team;
import utils.GameConfig;

/**
 * A component of ControlPanel: Display the current team color
 * @author Phan Vo
 *
 */
public class TeamColorPanel extends JPanel{
	
	private JTextField tfColor;
	
	public TeamColorPanel() {
		// TODO Auto-generated constructor stub
		super();
		
		tfColor = new JTextField();
		tfColor.setHorizontalAlignment(JTextField.CENTER);		
		tfColor.setPreferredSize(new Dimension(GameConfig.getControlsWidth()-40, GameConfig.getControlsWidth()-40));
		tfColor.setEditable(false);
		tfColor.setForeground(Color.WHITE);
		tfColor.setFont(new Font("Sans-serif", Font.BOLD, 80));

		JPanel pnContainer = new JPanel(new FlowLayout());
		pnContainer.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		pnContainer.add(tfColor);
		
	    TitledBorder titled = new TitledBorder("Team");
	    pnContainer.setBorder(titled);

		this.add(pnContainer);

	}
	
	public void setTeamColor(Color c) {
		tfColor.setBackground(c);
	}
	
	public void setAvailablePieces(int num) {
		tfColor.setText(String.valueOf(num));
	}

}
