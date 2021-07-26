package trab3.bubbles.pieces;

import java.util.function.Predicate;

public class Dummy implements Bubble {
	public int getLine()                               { return -1;       }
	public int getColumn()                             { return -1;       }
	public int getColor()                              { return -1;       }
	public boolean isSelected()                        { return false;    }
	public boolean selectIf(Predicate<Bubble> isEqual) { return false;    }
	public boolean moveTo( int l, int c )              { return false;    }
	public String toString()                           { return "Dummy"; }
	public void select( )                              {  }
	public void unselect()         { throw new UnsupportedOperationException("try unselect BORDER"); }
	public int compareTo(Bubble o) {
		throw new UnsupportedOperationException("Dummy is not comparable");
	}
}
