package trab3.bubbles.pieces;

import trab3.bubbles.Board;

import java.util.function.Predicate;

public class DiagonalBubble extends NeighborBubble {

	public DiagonalBubble(Board b, int l, int c, int cl) {
		super(b, l, c, cl);
	}

	public Dir[] getDirections() {
		return Dir.values();
	}

}
