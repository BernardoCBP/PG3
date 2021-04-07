package trab1.grupo3;

import trab1.grupo2.TransformException;

public class Song expands Artwork {

    public Song(int year, String name, Author a, int seconds) {
        if (seconds < 1) {
            throw new ArtworkException("Duration must be greater than 0");
        }
    }

    public Artwork getMatch() {

    }

    public String toString() {

    }

    public int getSeconds() {
        
    }

}
