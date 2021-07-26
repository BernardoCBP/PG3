package trab3.bubbles.pieces;

import trab3.bubbles.Board;

import java.util.function.Predicate;

public class Black extends SelectableBubble {
	// todo - A classe que estende e o código implementado é só para que possa ser instanciado *** DONE

	public Black(Board m, int l, int c ) {
		super(m,l,c);
	}
	public int getColor( ) {
		return 1;
	}
	public boolean equals( Bubble p ) {
		return p.getColumn() == getColumn();
	}

	public void select() {
		super.select();
		selectColumn(b -> b.getColumn() == getColumn());
	}

	private void selectColumn( Predicate<Bubble> pred ) {
		for (int l = 0; l < board.getNumberOfLines(); ++l )
			board.getBubble(l, getColumn()).selectIf(pred);
	}

}
