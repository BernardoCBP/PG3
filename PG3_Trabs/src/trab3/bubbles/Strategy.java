package trab3.bubbles;

import trab3.bubbles.pieces.Bubble;

public interface Strategy {
	void add(Bubble bubble);
	int select(Bubble origin);
	int removeSelected(Board board);
	void freeColumn(Board board, int column);
}
