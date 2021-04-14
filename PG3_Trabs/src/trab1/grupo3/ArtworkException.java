package trab1.grupo3;

public class ArtworkException extends RuntimeException{

    public ArtworkException(String msg) {
        super(msg);
    }

    public ArtworkException() {
        super("Invalid artwork");
    }

}