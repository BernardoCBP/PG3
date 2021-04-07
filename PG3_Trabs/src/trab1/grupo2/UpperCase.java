package trab1.grupo2;

public class UpperCase extends SingleTransform {

    // CONSTRUCTOR
    public UpperCase() {
        super("to upper");
    }

    // METHOD
    @Override
    protected String applyForEach( String s ) {
        return s.toUpperCase();
    }

}
