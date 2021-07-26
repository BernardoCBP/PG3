package trab3.bubbles.pieces;

import java.util.function.Predicate;

public interface Bubble {
	int getLine();
	int getColumn();
	int getColor();
	boolean isSelected();

	void unselect();
	void select();
	boolean selectIf( Predicate<Bubble> pred );
	boolean moveTo( int l, int c );
}
