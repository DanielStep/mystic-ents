package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.board.BoardData;
import model.board.Square;
import model.game.GameTurn;
import model.piece.Piece;
import model.piece.Team;
import utils.BoardUtils;
import utils.GameUtils;
import view.DialogView;
import view.MainMenuFrame;
import view.MediatorView;

/**
 * Responsible for turn handling
 * Via Timer and end turn handling
 * And managing UI view
 * 
 * Generate game pieces that will be passed to the Board
 *
 * @author skh, mark, pv
 *
 */

public class GameController implements Observer {

	//GAME CONTROL
	private GameTurn gameTimer;
	private BoardController boardController;
	private ActionController actionController;
	private AIHandler gameAI;
	private Boolean aiTurn = false;

	//UI
	private UIMediator uiMediator;

	//BOARD OBJECTS
	private Team currentTeam;
	
	private static ArrayList<Piece> gamePiecesList = new ArrayList<Piece>();

	public GameController() {
		uiMediator = UIMediator.getInstance();
		actionController = ActionController.getInstance();
		buildTimer();
		new MainMenuFrame(this);
	}

	public void newGame(boolean isWithAI) {
		boardController.init();		
		continueGame(isWithAI);
	}
	
	public void continueGame(boolean isWithAI) {
		boardController.buildBoard();
		boardController.getBoardFrame().setVisible(true);
		collectGamePieces();
		currentTeam = setCurrentTeam();		
		startGame(isWithAI);
	}
	
	public void startGame(boolean isWithAI) {
		if (isWithAI) {
			buildAI();
		}
		//Start Game running
		gameTimer.start();
		
		// save AI variable for save game
		boardController.getBoardData().setIsWithAI(isWithAI);
	}	
	
	private void buildAI() {
		gameAI = new AIHandler();
		//Check AI Status
		aiTurn = checkAIStatus();
	}

	/**
	 * The update method of the Observer pattern
	 * Watches the GameTurn timer to handle end turns, team change,
	 * And to update the UI
	 */	
	
	@Override
	public void update(Observable o, Object arg) {
		GameTurn gameTurn = (GameTurn) o;
		//Post condition exception if GameTurn is null, return (exit?)
		if (gameTurn == null) return;
		
		uiMediator.setPieceCount(getAvailablePieceCount());
		uiMediator.doUIUpdate(gameTurn);
		
		//AI Turn
		//Modulus turn count to slow AI down
		if (aiTurn) {
			if (gameTurn.getGameTimer() % 2 == 0) {
				gameAI.handleGameTurn(currentTeam);				
			}
		}		
		
		// when time is up
		if (gameTurn.getGameTimer() == 0) {
			handleEndTurn();
		}
	}

	/**
	 * EndTurn handler
	 * Called via update method when GameTurn.getGameTimer = 0
	 * A turn is ended via the timer value rather than calling a set function
	 * Gives better adherence to MVC pattern.
	 * 
	 */		
	private void handleEndTurn() {
		//reset ActionController
		actionController.resetActionCount();
		actionController.setActivePiece(null);
		
		//Change teams
		currentTeam = changeTeams();		
		boardController.getBoardData().setCurrentTeam(currentTeam);
		
		//Clear Board
		boardController.clearRangeCells();
		
		//Reset TraitValues of all pieces on board to base value
		boardController.getBoardData().resetPieceTraitValueToBase(gamePiecesList);		
		
		//Update UI
		uiMediator.setCurrentTeam(currentTeam);
		uiMediator.setPieceCount(getAvailablePieceCount());
		uiMediator.doUIEndTurn();

		// set game turn count;
		gameTimer.setCount(gameTimer.getCount()+1);
		
		//Check AI Status
		if (gameAI != null) {
			aiTurn = checkAIStatus();
		}
		
	}
	
	public Boolean loadGame(){
		BoardData data = GameUtils.getInstance().loadGame();
		if (GameUtils.getInstance().loadGame() != null) {
			boardController.getBoardData().setIsWithAI(data.getIsWithAI());
			boardController.getBoardData().setCurrentTeam(data.getCurrentTeam());
			boardController.getBoardData().setBoardArray(data.getBoardArray());
			boardController.getBoardData().doCellsUpdate();
			return true;
		} else {
			DialogView.getInstance().showInformation("Save game not found!");
			return false;
		}
	}
	
	private Team changeTeams() {
		return GameUtils.getInstance().getNextTeam(gamePiecesList, currentTeam);
	}
	
	private Boolean checkAIStatus() {
		//Check AI Status
		return gameAI.checkAIStatus(currentTeam);
	}
	
	private Team setCurrentTeam() {
		Team team = boardController.getBoardData().getCurrentTeam();
		team = team == null ? Team.values()[0] : team;
		uiMediator.setCurrentTeam(team);
		return team;
	}
	

	private int getAvailablePieceCount() {		
		return GameUtils.getInstance().getAvailablePieceCount(gamePiecesList, currentTeam);
	}
		
	public void updatePieceInformation(Piece pce) {
		// Update Piece Statistics on Selection
		uiMediator.updatePieceInformation(pce);
	}
	
	private void collectGamePieces() {		
		gamePiecesList = GameUtils.getInstance().getGamePieces(boardController.getBoardData().getBoardArray());
	}
	
	//ALL these need to added to Command Pattern
	public ArrayList <Square> getTowerList() {		
		return GameUtils.getInstance().getTowerList(boardController.getBoardData().getBoardArray());
	}
	public ArrayList <Square> getRangeList() {		
		return BoardUtils.getInstance().getRangeList(boardController.getBoardData().getBoardArray());
	}

	public void setMessage(String msg) {
		uiMediator.setMoveInfoMessage(msg);
	}
	
	private void buildTimer() {
		gameTimer = new GameTurn();
		observe(gameTimer);
	}	

	public UIMediator getUiMediator() {
		return uiMediator;
	}
	
	public void setUiMediator(UIMediator uiMediator) {
		this.uiMediator = uiMediator;
	}
	
	public void observe(Observable o) {
		o.addObserver(this);
	}

	public ArrayList<Piece> getGamePiecesList() {
		return gamePiecesList;
	}

	public void setGamePiecesList(ArrayList<Piece> piecesList) {
		gamePiecesList = piecesList;
	}	

	public void setBoardController(BoardController gameBoard) {
		this.boardController = gameBoard;
	}
	
	public void setActionController(ActionController actionController) {
		this.actionController = actionController;
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

	public BoardController getBoardController() {
		return boardController;
	}	
	
	public Boolean getAiTurn() {
		return aiTurn;
	}

	public void setAiTurn(Boolean aiTurn) {
		this.aiTurn = aiTurn;
	}
}