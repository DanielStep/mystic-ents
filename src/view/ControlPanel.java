package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import model.GameConfig;
import model.GameTurn;
import model.Piece;
import model.Game;
/**
 * Display board game info: timer, team color in play, remaining pieces
 * 							selected piece, end turn mechanism
 * @author Phan Vo
 *
 */
public class ControlPanel extends JPanel{
	private TimePanel pnTime;
	private TeamColorPanel pnTeamColor;
	private AvailablePiecePanel pnAvailablePiece;
	private PieceInfoPanel pnPieceInfo;
	private EndTurnPanel pnEndTurn;

	private Game gameUtils;
	
	public ControlPanel() {
		
		super();
		
		gameUtils = Game.getInstance();
		
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
	    
	}
	
	public void doUIEndTurn() {
		//reset the Piece info panel on switch team.
		pnPieceInfo.resetPieceInformation();
		
		// auto end the current player's turn
		pnEndTurn.executeEndTurn();
	}
	
	public void doUIUpdate(GameTurn gameTurn) {
		// update time on ControlPanel view		
		pnTime.setTime(gameTurn.getGameTimer());

		// set end turn conditions
		pnEndTurn.setGameTurn(gameTurn);
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
	
	public void setPieceCount(int count) {
		// update available pieces for the current team 
		pnAvailablePiece.setAvailablePieces(count);
	}	

	public void setCurrentTeam(String team) {
		// update team color on ControlPanel view based on current team enum
		pnTeamColor.setTeamColor(gameUtils.stringToColor(team, Color.BLACK));
	}	
	
	public void updatePieceInformation(Piece pce) {
		
	}
	
}
