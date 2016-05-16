package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import controller.BoardController;
import model.game.GameTurn;
import model.piece.Piece;
import model.piece.Team;
import utils.BoardUtils;
import utils.GameConfig;

/**
 * Display board game info: timer, team color in play, remaining pieces selected
 * piece, end turn mechanism
 * 
 * @author Phan Vo
 *
 */
public class GameControls extends JPanel {

	private int panelWidth;

	private TimePanel pnTime;
	private TeamColorPanel pnTeamColor;
	private AvailablePiecePanel pnAvailablePiece;
	private PieceInfoPanel pnPieceInfo;
	private EndTurnPanel pnEndTurn;
	private UndoPanel pnUndo;
	private SaveGamePanel pnSaveGame;
	private MoveInfoPanel pnMoveInfo;

	private BoardUtils boardUtils;

	public GameControls(BoardController boardController) {

		super();
		boardUtils = BoardUtils.getInstance();

		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setPreferredSize(new Dimension(GameConfig.getControlsWidth(), GameConfig.getDefaultHeight()));

		pnTime = new TimePanel();
		this.add(pnTime);

		pnTeamColor = new TeamColorPanel();
		this.add(pnTeamColor);

		// pnAvailablePiece = new AvailablePiecePanel();
		// this.add(pnAvailablePiece);

		pnPieceInfo = new PieceInfoPanel();
		this.add(pnPieceInfo);

		pnUndo = new UndoPanel(boardController);
		this.add(pnUndo);

		pnSaveGame = new SaveGamePanel();
		this.add(pnSaveGame);

		pnEndTurn = new EndTurnPanel();
		this.add(pnEndTurn);

		pnMoveInfo = MoveInfoPanel.getInstance();
		this.add(pnMoveInfo);

	}

	public void doUIEndTurn() {
		// reset the Piece info panel on switch team.
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

	public void disableAllButtons() {
		pnSaveGame.getSaveButton().setEnabled(false);
		disableUndoButton();
		pnEndTurn.getEndTurnButton().setEnabled(false);
	}

	public void disableUndoButton() {
		pnUndo.getUndoButton().setEnabled(false);
	}

	public void enableUndoButton() {
		pnUndo.getUndoButton().setEnabled(true);
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

	public SaveGamePanel getSaveGamePanel() {
		return pnSaveGame;
	}

	public AvailablePiecePanel getAvailablePiecePanel() {
		return pnAvailablePiece;
	}

	public PieceInfoPanel getPieceInfoPanel() {
		return pnPieceInfo;
	}

	public MoveInfoPanel getMoveInfoPanel() {
		return pnMoveInfo;
	}

	public void setMoveInfoPanel(MoveInfoPanel pnMoveInfo) {
		this.pnMoveInfo = pnMoveInfo;
	}

	public void setPieceCount(int count) {
		// update available pieces for the current team
		pnTeamColor.setAvailablePieces(count);
	}

	public void setCurrentTeam(Team team) {
		// update team color on ControlPanel view based on current team enum
		pnTeamColor.setTeamColor(new Color(team.getRed(), team.getGreen(), team.getBlue()));

		// set undo button
		if (team.getUndoNum() < 1) {
			disableUndoButton();
		} else {
			enableUndoButton();
		}
	}

	public int getPanelWidth() {
		return panelWidth;
	}

	public void setPanelWidth(int panelWidth) {
		this.panelWidth = panelWidth;
	}

}
