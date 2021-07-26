package trab3.bubbles.pieces;
import trab3.bubbles.Board;
public abstract class AbstractBubble implements Bubble {
	protected final Board board;
	private int line, column;
	public AbstractBubble(Board b, int l, int c ) {
		board = b; line=l; column= c; 
	}
	public final int getLine()    { return line;    }
	public final int getColumn()  { return column;  }

	public boolean moveTo( int l, int c ) {
		line=l; column=c;
		board.putBubble(line, column, this);
		return true;
	}
	
	public boolean equals( Object o ) {
		if ( !(o instanceof Bubble) ) return false;
		Bubble b = (Bubble) o;
		return getColumn()==b.getColumn() && getLine()==b.getLine();
	}

	public String toString()  {
		return getLine()+":"+getColumn();
	}
}
