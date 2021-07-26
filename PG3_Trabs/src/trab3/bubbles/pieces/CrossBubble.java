package trab3.bubbles.pieces;

import trab3.bubbles.Board;

import java.util.function.Predicate;

public class CrossBubble extends NeighborBubble {

	public CrossBubble(Board b, int l, int c, int cl) {
		super(b, l, c, cl);
	}

	public Dir[] getDirections() {
		return new Dir[] { Dir.UP, Dir.DOWN, Dir.LEFT, Dir.RIGHT };
	}

}
