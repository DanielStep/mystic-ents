package model.board;

import java.util.Stack;

/**
 * Care taker for the board
 * 
 * @author skh
 *
 */

public class BoardCareTaker {

	final Stack<BoardMemento> mementos = new Stack<BoardMemento>();

	public BoardMemento getMemento() {
		if (!mementos.empty()) {
			BoardMemento boardMemento = mementos.pop();
			return boardMemento;
		}
		return null;
	}

	public void addMemento(BoardMemento boardMemento) {
		
		//Only have to save up to 6 states
		if (mementos.size() > 6) {
			mementos.pop();
		}
		mementos.add(boardMemento);
	}
}
