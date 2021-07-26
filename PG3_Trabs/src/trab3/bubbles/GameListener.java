package trab3.bubbles;

import trab3.bubbles.pieces.Bubble;

public interface GameListener extends BubbleListener {
	void scoreChange(Game g);
	void gameStop(Game g);
	void gameStart(Game g);
}
