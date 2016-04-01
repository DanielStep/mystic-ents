package main;

import controller.*;

public class GameMain implements Runnable {

	public static void main(String[] args) {
		GameController gameController = new GameController();
		BoardController boardController = new BoardController(gameController);
		gameController.setUIObjects(boardController);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
