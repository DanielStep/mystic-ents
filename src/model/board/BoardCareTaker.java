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

	private static BoardCareTaker instance;

	public static synchronized BoardCareTaker getInstance() {
		if (instance == null) {
			instance = new BoardCareTaker();
		}
		return instance;
	}

	public int getMementosSize() {
		return mementos.size();
	}

	public BoardMemento getMemento() {
		return mementos.pollLast();
	}

	public void addMemento(BoardMemento boardMemento) {

		// Only have to save up to 6 states, remove old states
		// if (mementos.size() > 9) {
		// mementos.poll();
		// }
		System.out.println("Adding Memento:" + mementos.size());
		// boardMemento.print();
		mementos.add(boardMemento);
		System.out.println("Memento size:" + mementos.size());

	}
}
