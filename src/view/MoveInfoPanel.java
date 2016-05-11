package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import utils.GameConfig;

/**
 * A component of ControlPanel: Display the current team color
 * @author Mark
 *
 */

public class MoveInfoPanel extends JPanel{
	
	private static MoveInfoPanel instance;
	
	private JTextField tfColor;
	
	private MoveInfoPanel() {}	
	
	/**
	 * thread-safe for getting the only instance of the object
	 * reuse the DialogView to display in-game message
	 * @return
	 */
	public static synchronized MoveInfoPanel getInstance() {
		if (instance == null) {
			instance = new MoveInfoPanel();
			instance.buildPanel();
		}
		return instance;
	}
	
	private void buildPanel() {		
		
		//super();

		tfColor = new JTextField();
		tfColor.setHorizontalAlignment(JTextField.CENTER);		
		tfColor.setPreferredSize(new Dimension(GameConfig.getControlsWidth()-40, 30));
		tfColor.setEditable(false);
		tfColor.setForeground(Color.BLACK);
		tfColor.setBackground(Color.WHITE);
		tfColor.setFont(new Font("Sans-serif", Font.BOLD, 10));

		JPanel pnContainer = new JPanel(new FlowLayout());
		pnContainer.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		pnContainer.add(tfColor);
		
	    TitledBorder titled = new TitledBorder("Move status");
	    pnContainer.setBorder(titled);

		this.add(pnContainer);

	}
	
	public void setMessage(String msg) {
		tfColor.setText(msg);
	}

}
