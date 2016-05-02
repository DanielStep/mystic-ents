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

	public BoardCareTaker() {
	}

	public int getMementosSize() {
		return mementos.size();
	}

	public BoardMemento getMemento() {
		// System.out.println("Undoing - getMemento dummy: " + mementos.size());

		//mementos.pollLast();
		mementos.pollLast();
		// boardMemento.print();
		System.out.println("Undoing - getMemento: " + mementos.size());
		BoardMemento boardMemento = mementos.pollLast();
		boardMemento.print();
		return boardMemento;
	}

	// TODO: Deference the array
	public void addMemento(BoardMemento boardMemento) {
		// if (mementos.size() > 0) {
		// mementos.getFirst().print();
		// }

		// Only have to save up to 6 states, remove old states
		// if (mementos.size() > 9) {
		// mementos.poll();
		// }
		System.out.println("Adding Memento:" + mementos.size());
		boardMemento.print();
		mementos.add(boardMemento);
		System.out.println("Memento size:" + mementos.size());

	}
}
