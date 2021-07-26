package trab3.bubbles;

import trab3.bubbles.pieces.*;

public interface Board extends BubbleListener {
	int getNumberOfLines();
	int getNumberOfColumns();
	Bubble getBubble(int l, int c);
	void putBubble(int l, int c, Bubble b);
	void putHole(int l, int c);
}
