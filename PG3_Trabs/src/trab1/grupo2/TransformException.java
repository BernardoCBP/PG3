package trab1.grupo2;

public class TransformException extends java.lang.Exception {

    private final Transform transform;

    public TransformException(Transform t, String msg) {
        this.transform = t;
    }

    public Transform getTransform() {
        return transform;
    }
    /*
    @Override
    public String getMessage() {
        return transform.toString() +
    }
    */
}
