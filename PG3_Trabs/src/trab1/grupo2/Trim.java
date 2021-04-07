package trab1.grupo2;

public class Trim extends SingleTransform {

    public Trim() {
        super("trim");
    }

    @Override
    protected String applyForEach(String s) {
        return s.trim();
    }
}
