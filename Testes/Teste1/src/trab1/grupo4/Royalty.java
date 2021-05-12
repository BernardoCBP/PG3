package trab1.grupo4;

import trab1.grupo3.Artwork;

public abstract class Royalty {

    private Artwork artwork;

    protected Royalty( Artwork aw ) {
        this.artwork = aw;
    }

    public Artwork getArtwork() {
        return this.artwork;
    }

    public abstract int getValue();

    public abstract boolean isPayed();

    public abstract void pay() throws RoyaltyException;

    @Override
    public boolean equals( Object o ) {
        if( this == o ) return true;
        if( !(o instanceof Royalty) ) return false;
        Royalty r = (Royalty) o;
        return this.artwork.equals(r.getArtwork()) && this.getValue() == r.getValue();
    }

    @Override
    public String toString() {
        return "\u20ac" + this.getValue() + " --> " + this.artwork.toString(); //unicode value for euro: U+20AC
    }

}
