package trab3.bubbles.pieces;
import trab3.bubbles.*;

import java.util.function.Predicate;

public abstract class SelectableBubble extends AbstractBubble {
	enum Dir {
		DOWN( 1, 0), UP(-1, 0), LEFT(0, -1), RIGHT(0, 1),
		LEFT_DOWN( 1, -1), LEFT_UP(-1, -1), RIGHT_UP(-1, 1), RIGHT_DOWN(1, 1);
		public final int deltaLine, deltaColumn;

		Dir( int dl, int dc) {
			deltaLine = dl;
			deltaColumn = dc;
		}
	};

	private boolean selected = false;
	public SelectableBubble(Board b, int l, int c ) {
		super(b, l, c);
	}

	public final boolean isSelected() {
		return selected;
	}

	public void unselect() {
		selected = false; board.unselected( this );
	}

	public void select() {
		setSelected();
	}

	public boolean selectIf( Predicate<Bubble> pred ) {
		if ( !isSelected() && pred.test( this )) {
			setSelected();
			return true;
		}
		return false;
	}

	protected void setSelected() {
		selected = true; board.selected(this);
	}

	public String toString() {
		return super.toString() +
				" - " + getColor() +
				(isSelected() ? "": " not") +" selected";
	}

}