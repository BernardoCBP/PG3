/*package trab1.grupo3;

import java.util.Objects;

public abstract class Artwork implements Author {

    public int year;
    private String name;

    protected Artwork(int year, String name, Author a) {
        this.year = year;
        this.name = name;

    }

    public Author getAuthor() {
        return null;
    }

    public abstract Artwork getMatch(Artwork a);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artwork artwork = (Artwork) o;
        return year == artwork.year && name.equals(artwork.name);
    }

    public String toString() {

    }

    public static Artwork getArtwork(Artwork a, Artwork ... aws) {

    }


}
*/