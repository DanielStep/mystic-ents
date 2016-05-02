package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.board.BoardData;
import model.board.Square;
import model.game.GameTurn;
import model.piece.Piece;
import model.piece.Team;
import utils.GameConfig;
import utils.GameUtils;
import view.ControlPanel;
import view.DialogView;
import view.MainMenuFrame;

/**
 * Responsible for turn handling Via Timer and end turn handling And managing UI
 * view
 * 
 * Generate game pieces that will be passed to the Board
 *
 * @author skh, mark, pv
 *
 */

public class GameController implements Observer {

	// GAME CONTROL
	private GameTurn gameTimer;
	private BoardController gameBoard;
	private ActionController pieceController;

	// UI
	private ControlPanel controlPanel;

	// BOARD OBJECTS
	private Team currentTeam;
	private int currentTeamIndex = 0;
	
	private static int fullTurnCounter=0;

	private static ArrayList<Piece> gamePiecesList = new ArrayList<Piece>();

	public GameController() {
		buildTimer();
		new MainMenuFrame(this);
	}

	public void startGame() {
		gameBoard.init();
		continueGame();
	}
	

	public void continueGame() {
		gameBoard.buildBoard();
		gameBoard.getBoardFrame().setVisible(true);
		generateGamePieces();
		setControlObjects();
		currentTeam = Team.values()[currentTeamIndex];
		controlPanel.setCurrentTeam(currentTeam.name());
		startTimer();
		loadTeamColorFromSaveGame();
	}

	/**
	 * The update method of the Observer pattern Watches the GameTurn timer to
	 * handle end turns, team change, And to update the UI
	 */

	@Override
	public void update(Observable o, Object arg) {

		GameTurn gameTurn = (GameTurn) o;
		// Post condition exception if GameTurn is null, return (exit?)
		if (gameTurn == null)
			return;

		controlPanel.setPieceCount(getAvailablePieceCount());
		controlPanel.doUIUpdate(gameTurn);

		// when time is up
		if (gameTurn.getGameTimer() == 0) {
			handleEndTurn();
		}
	}

	/**
	 * EndTurn handler Called via update method when GameTurn.getGameTimer = 0 A
	 * turn is ended via the timer value rather than calling a set function
	 * Gives better adherence to MVC pattern.
	 * 
	 */
	private void handleEndTurn() {		
		
//		increaseFullTurnCounter();
//		// add game state to memento after each turn TODO: where to place
//		if(GameController.fullTurnCounter==2){
//			
//			resetFullTurnCounter();
//		}	
		
		// reset action counter
		pieceController.getInstance().resetActionCount();

		// set game turn count;
		int newCount = gameTimer.getCount();
		newCount++;
		gameTimer.setCount(newCount);

		// Change teams
		currentTeam = changeTeams();

		// Reset Board
		gameBoard.clearRangeCells();

		// Update save data for current team
		BoardData.getInstance().setCurrentTeam(currentTeam);
		
		//Reset TraitValues of all pieces on board to base value
		BoardData.getInstance().resetPieceTraitValueToBase();
		
		//Update UI
		controlPanel.setCurrentTeam(currentTeam.name());	

		controlPanel.doUIEndTurn();
		
		gameBoard.saveToMemento();

	}

	private Team changeTeams() {
		ArrayList<Team> tList = new ArrayList<Team>(getAvailableTeamList());
		// int a = tList.indexOf(currentTeam);
		if (currentTeamIndex == tList.size() - 1) {
			currentTeamIndex = 0;
		} else {
			currentTeamIndex++;
		}

		System.out.println("New team: " + currentTeamIndex + " of " + tList.size());
		return Team.values()[currentTeamIndex];
	}

	public Boolean loadGame() {
		Object gameState = GameUtils.getInstance().loadGameData();
		if (gameState != null) {
			BoardData data = (BoardData) gameState;
			Team teamColor = data.getCurrentTeam();
			System.out.println("---------team color from save file = " + teamColor);

			// set team color from save file
			if (teamColor != null) {
				BoardData.getInstance().setCurrentTeam(teamColor);
				setCurrentTeam(teamColor);
			}

			GameConfig.setROW_COL(data.getBoardArray().length);
			gameBoard.getBoardData().setBoardArray(data.getBoardArray());
			gameBoard.getBoardData().doCellsUpdate();
			return true;
		} else {
			DialogView.getInstance().showInformation("Save game not found!");
			return false;
		}
	}

	public void loadTeamColorFromSaveGame() {
		Team teamEnum = BoardData.getInstance().getCurrentTeam();
		if (teamEnum != null) {
			// load current team from save data
			controlPanel.setCurrentTeam(teamEnum.name());
		}
	}

	private ArrayList<Team> getAvailableTeamList() {
		ArrayList<Team> tList = new ArrayList<Team>();
		for (Piece piece : gamePiecesList) {
			if (!tList.contains(piece.getTeam())) {
				tList.add((Team) piece.getTeam());
			}
		}
		return tList;
	}

	private int getAvailablePieceCount() {
		int count = 0;
		for (Piece piece : gamePiecesList) {
			if (currentTeam == piece.getTeam()) {
				count++;
			}
		}
		return count;
	}

	public void updatePieceInformation(Piece pce) {
		// Update Piece Statistics on Selection
		controlPanel.getPieceInfoPanel().updatePieceInformation(pce);
	}

	private void generateGamePieces() {
		Square[][] gameData = gameBoard.getBoardData().getBoardArray();
		for (int i = 0; i < gameData.length; i++) {
			for (int j = 0; j < gameData[i].length; j++) {
				if (gameData[i][j].getOccupant() != null) {
					gamePiecesList.add(gameData[i][j].getOccupant());
				}
			}
		}

	}

	public void setControlObjects() {
		setControlPanel(gameBoard.getBoardFrame().getControlPanel());
	}

	private void buildTimer() {
		gameTimer = new GameTurn();
		observe(gameTimer);
	}

	public void startTimer() {
		gameTimer.start();
	}

	public void stopTimer() {
		gameTimer.stop();
	}

	public void observe(Observable o) {
		o.addObserver(this);
	}

	public static ArrayList<Piece> getGamePiecesList() {
		return gamePiecesList;
	}

	public static void setGamePiecesList(ArrayList<Piece> piecesList) {
		gamePiecesList = piecesList;
	}

	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}

	public void setBoardController(BoardController gameBoard) {
		this.gameBoard = gameBoard;
	}

	public void setPieceActionController(ActionController pieceController) {
		this.pieceController = pieceController;
	}

	public Team getCurrentTeam() {
		return currentTeam;
	}

	public void setCurrentTeam(Team currentTeam) {
		this.currentTeam = currentTeam;
	}

	public GameTurn getGameTurn() {
		return gameTimer;
	}

	public BoardController getGameBoard() {
		return gameBoard;
	}
	
	public void increaseFullTurnCounter(){
		GameController.fullTurnCounter++;
	}
	
	public void resetFullTurnCounter(){
		GameController.fullTurnCounter=0;
	}

}