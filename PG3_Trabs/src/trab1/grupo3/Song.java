package trab1.grupo3;

public class Song extends Artwork {

    private final int seconds;

    public Song(int year, String name, Author author, int seconds) {
        super(year, name, author);
        if (seconds < 1) {
            throw new ArtworkException("Duration must be greater than 0");
        }
        this.seconds = seconds;
    }

    public Artwork getMatch(Artwork a) {
        return this.equals(a) ? this : null;
    }

    @Override
    public String toString() {
        return super.toString() + "[" + String.format("%02d", this.getSeconds()/60) + ":" + String.format("%02d", this.getSeconds()%60) + "]";
    }

    public int getSeconds() {
        return this.seconds;
    }

}


