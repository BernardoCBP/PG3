package trab3.bubbles;

import trab3.bubbles.pieces.Bubble;

public interface BubbleListener {
	void selected(Bubble b);
	void unselected(Bubble b);
}
