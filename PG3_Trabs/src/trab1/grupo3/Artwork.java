package trab1.grupo3;

public abstract class Artwork  {

    public final int year;
    private final String name;
    private final Author author;

    protected Artwork( int year, String name, Author author ) {
        this.year = year;
        this.name = name;
        this.author = author;
    }

    public Author getAuthor() {
        return this.author;
    }

    public abstract Artwork getMatch( Artwork a );

    @Override
    public boolean equals( Object o ) {
        if( this == o ) return true;
        if( !(o instanceof Artwork) ) return false;
        Artwork artwork = (Artwork) o;
        return this.year == artwork.year && this.name.equals(artwork.name) && this.author.equals(artwork.author);
    }

    @Override
    public String toString() {
        return this.name + " (" + this.author.getName() + ") ";
    }

    public static Artwork getArtwork(Artwork a, Artwork ... aws) {

        for( Artwork art: aws ) {
            Artwork match = art.getMatch(a);
            if(  match != null ) {
                return match;
            }
        }
        return null;
    }

}
