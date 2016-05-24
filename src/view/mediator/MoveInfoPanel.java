package view.mediator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import utils.GameConfig;

/**
 * A component of ControlPanel: Display the current move status
 * @author Mark
 *
 */

public class MoveInfoPanel extends JPanel{
	
	private static MoveInfoPanel instance;
	
	private JTextArea tfInfo;

	private JScrollPane scrollbar = new JScrollPane();
	
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
		
		tfInfo = new JTextArea();

		scrollbar.setBorder(BorderFactory.createEmptyBorder());
		scrollbar.getViewport().setBackground(Color.WHITE);
		scrollbar.setViewportView(tfInfo);
		scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollbar.getVerticalScrollBar().setUnitIncrement(50);
		scrollbar.setPreferredSize(new Dimension(GameConfig.getControlsWidth()-30, 100));
		
		tfInfo.setEditable(false);
		tfInfo.setForeground(Color.BLACK);
		tfInfo.setBackground(Color.WHITE);
		tfInfo.setFont(new Font("Sans-serif", Font.BOLD, 14));
		tfInfo.setLineWrap(true);
		tfInfo.setWrapStyleWord(true);

		/*tfColor = new JTextField();
		tfColor.setHorizontalAlignment(JTextField.CENTER);
		tfColor.setPreferredSize(new Dimension(GameConfig.getControlsWidth()-40, 90));
		tfColor.setEditable(false);
		tfColor.setForeground(Color.BLACK);
		tfColor.setBackground(Color.WHITE);
		tfColor.setFont(new Font("Sans-serif", Font.BOLD, 16));*/

		JPanel pnContainer = new JPanel(new FlowLayout());
		pnContainer.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		pnContainer.setPreferredSize(new Dimension(GameConfig.getControlsWidth()-20, 130));
		pnContainer.add(scrollbar);
		
		DefaultCaret caret2 = (DefaultCaret)tfInfo.getCaret();
		caret2.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
	    TitledBorder titled = new TitledBorder("Move status");
	    pnContainer.setBorder(titled);

		this.add(pnContainer);

	}
	
	public void setMessage(String msg) {
		tfInfo.append(msg+"\n");
	}

}
