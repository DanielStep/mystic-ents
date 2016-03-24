package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
/**
 * Display board game info and controls
 * @author Phan Vo
 *
 */
public class ControlFrame extends JFrame{
	
	public ControlFrame() {
		// TODO Auto-generated constructor stub
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setPreferredSize(new Dimension(220, 500));
	    
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
	    
	    this.pack();
	    this.setVisible(true);
	}
}
