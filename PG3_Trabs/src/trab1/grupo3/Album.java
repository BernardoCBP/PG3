package trab1.grupo3;

import java.util.Arrays;
import java.util.List;

public class Album extends Artwork {

    protected List<Song> songs;

    public Album( String name, Author author, Song ... s ) {
        super( Album.getLatest(s).year, name, author);
        if (s.length < 2) {
            throw new ArtworkException("The album must have at least two songs");
        }
        Arrays.sort(s, (o1, o2) -> {
            if (o1.year == o2.year)
                return o1.toString().compareTo(o2.toString());
            return o1.year - o2.year;
        });

        this.songs = Arrays.asList( s );
    }

    public Artwork getMatch ( Artwork a ) {
        for (Song song : this.songs) {
            if (song.equals(a))
                return song;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder catString = new StringBuilder();
    
        catString.append(this.year).append(" - ").append(super.toString().trim()).append("\n");
        for(int i=0; i < this.songs.size(); i++ ) {
            catString.append("\t").append(i+1).append(" - ");
            catString.append(this.getSongs().get(i).toString()).append("\n");
        }
        return catString.toString();
    }

    public List<Song> getSongs() {
        return this.songs;
    }

    public static Song getLatest(Song ... s) {

        if( s != null && s.length > 0 ) {
            Song latest = s[0];
            for(int i = 0; i < s.length-1; i++) {
                if(s[i+1].year > latest.year )
                    latest = s[i+1];
            }
            return latest;
        }
        return null;
    }

}
