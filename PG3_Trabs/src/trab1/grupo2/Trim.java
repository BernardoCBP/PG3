package trab1.grupo2;

public class Trim extends SingleTransform {

    // CONSTRUCTOR
    public Trim() {
        super("trim");
    }

    // METHODS
    @Override
    protected String applyForEach( String s ) {
        return s.trim();
    }

}
