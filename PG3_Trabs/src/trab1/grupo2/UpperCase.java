package trab1.grupo2;

public class UpperCase extends SingleTransform {

    public UpperCase() {
        super("to upper");
    }

    @Override
    protected String applyForEach(String s) {
        return s.toUpperCase();
    }
}
