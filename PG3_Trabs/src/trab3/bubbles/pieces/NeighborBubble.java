package trab3.bubbles.pieces;

import trab3.bubbles.Board;

import java.util.function.Predicate;

public abstract class NeighborBubble extends SelectableBubble {

    private final int color;

    public NeighborBubble(Board b, int l, int c, int cl) {
        super(b, l,c);
        color = cl;
    }

    public int getColor() {
        return color;
    }

    public abstract Dir[] getDirections();

    public boolean equals( Bubble p ) {
        return p.getColor() == getColor();
    }

    public void select( ) {
        selectIf(this::equals);
    }

    public boolean selectIf( Predicate<Bubble> pred ) {
        if ( super.selectIf( pred ) ) {
            selectAround( pred );
            return true;
        }
        return false;
    }

    protected void selectAround( Predicate<Bubble> pred ) {
        for ( SelectableBubble.Dir d: this.getDirections() )
            board.getBubble(getLine()+d.deltaLine, getColumn()+d.deltaColumn).selectIf(pred);
    }


}
