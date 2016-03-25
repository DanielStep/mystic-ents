package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import model.GameConfig;
/**
 * Display board game info and controls
 * @author Phan Vo
 *
 */
public class ControlPanel extends JPanel{
	
	public ControlPanel() {
		// TODO Auto-generated constructor stub
		super();
//		this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
//	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    this.setPreferredSize(new Dimension(220, 500));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setPreferredSize(new Dimension(GameConfig.getDefaultControlPanelWidth(), GameConfig.getDefaultHeight()));
	    
	    TimePanel a = new TimePanel();
	    this.add(a);
	    
	    TeamColorPanel b = new TeamColorPanel();
	    this.add(b);
	    
	    AvailablePiecePanel c = new AvailablePiecePanel();
	    this.add(c);
	    
	    PieceInfoPanel d = new PieceInfoPanel();
	    this.add(d);
	    
	    EndTurnPanel e = new EndTurnPanel();
	    this.add(e);
	    
//	    this.pack();
//	    this.setVisible(true);
	}
}
