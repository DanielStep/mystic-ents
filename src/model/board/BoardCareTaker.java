package model.board;

import java.util.LinkedList;

/**
 * Care taker for the board
 * 
 * @author skh
 *
 */

public class BoardCareTaker {

	final LinkedList<BoardMemento> mementos = new LinkedList<>();

	public BoardMemento getMemento() {
		if (mementos.size()>1) {
			mementos.pollLast();
			BoardMemento boardMemento = mementos.pollLast();
			return boardMemento;
		}
		return null;
	}

	public void addMemento(BoardMemento boardMemento) {
		
		//Only have to save up to 6 states, remove old states
		if (mementos.size() > 6) {
			mementos.poll();
		}
		mementos.add(boardMemento);
	}
}
