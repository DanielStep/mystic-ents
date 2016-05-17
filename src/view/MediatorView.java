package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.BoardController;
import controller.UIMediator;

import model.game.GameTurn;
import model.piece.Piece;
import model.piece.Team;
import utils.BoardUtils;
import utils.GameConfig;
/**
 * Display board game info: timer, team color in play, remaining pieces
 * 							selected piece, end turn mechanism
 * @author Phan Vo
 *
 */
public class MediatorView extends JPanel {
	
	public MediatorView() {
		
		super();		
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setPreferredSize(new Dimension(GameConfig.getControlsWidth(), GameConfig.getDefaultHeight()));
	    
		TimePanel pnTime = new TimePanel();	    
		TeamColorPanel pnTeamColor = new TeamColorPanel();
		PieceInfoPanel pnPieceInfo = new PieceInfoPanel();
		UndoPanel pnUndo = new UndoPanel();
		SaveGamePanel pnSaveGame = new SaveGamePanel();
		EndTurnPanel pnEndTurn = new EndTurnPanel();
		MoveInfoPanel pnMoveInfo = MoveInfoPanel.getInstance();	    
	    
	    this.add(pnTime);
	    this.add(pnTeamColor);
	    this.add(pnPieceInfo);	    
	    this.add(pnUndo);	    
	    this.add(pnSaveGame);	    
	    this.add(pnEndTurn);	    
	    this.add(pnMoveInfo);
	    
	    UIMediator.getInstance().registerColleagues(
	    		pnTime, 
	    		pnTeamColor,
	    		pnPieceInfo, 
	    		pnUndo, 
	    		pnSaveGame,
	    		pnEndTurn,
	    		pnMoveInfo
	            );	    
	    
	}
	
}
