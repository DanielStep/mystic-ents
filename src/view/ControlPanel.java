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
	private TimePanel pnTime;
	private TeamColorPanel pnTeamColor;
	private AvailablePiecePanel pnAvailablePiece;
	private PieceInfoPanel pnPieceInfo;
	private EndTurnPanel pnEndTurn;
	
	public ControlPanel() {
		// TODO Auto-generated constructor stub
		super();
//		this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
//	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    this.setPreferredSize(new Dimension(220, 500));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setPreferredSize(new Dimension(GameConfig.getDefaultControlPanelWidth(), GameConfig.getDefaultHeight()));
	    
		pnTime = new TimePanel();
	    this.add(pnTime);
	    
	    pnTeamColor = new TeamColorPanel();
	    this.add(pnTeamColor);
	    
	    pnAvailablePiece = new AvailablePiecePanel();
	    this.add(pnAvailablePiece);
	    
	    pnPieceInfo = new PieceInfoPanel();
	    this.add(pnPieceInfo);
	    
	    pnEndTurn = new EndTurnPanel();
	    this.add(pnEndTurn);
	    
//	    this.pack();
//	    this.setVisible(true);
	}
	
	public TimePanel getTimePanel() {
		return pnTime;
	}
	
	public TeamColorPanel getTeamColorPanel() {
		return pnTeamColor;
	}
	
	public EndTurnPanel getEndTurnPanel() {
		return pnEndTurn;
	}
	
	public AvailablePiecePanel getAvailablePiecePanel() {
		return pnAvailablePiece;
	}
	
	public PieceInfoPanel getPieceInfoPanel() {
		return pnPieceInfo;
	}	
}
