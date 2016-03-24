package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * A component of ControlFrame
 * @author Phan Vo
 *
 */
public class TeamColorPanel extends JPanel{
	private JTextField tfColor;
	
	public TeamColorPanel() {
		// TODO Auto-generated constructor stub
		super(true);
		
		tfColor = new JTextField();
		tfColor.setHorizontalAlignment(JTextField.CENTER);
		tfColor.setPreferredSize(new Dimension(50, 50));
		tfColor.setBackground(Color.BLUE);
		tfColor.setEditable(false);

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
}
