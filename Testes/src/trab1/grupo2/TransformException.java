package trab1.grupo2;

public class TransformException extends Exception {

    // VARIABLES
    private final Transform transform;

    // CONSTRUCTOR
    public TransformException( Transform t, String msg ) {
        super(t + " " + msg);                             // concatenate the transform with the message
        this.transform = t;
    }

    // METHODS
    public Transform getTransform() {
        return transform;
    }

}
