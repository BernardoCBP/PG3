package trab1.grupo2;

public class UpperCase extends SingleTranform {

    public UpperCase() {
        super("to upper");
    }

    @Override
    protected String applyForEach(String s) {
        return s.toUpperCase();
    }
}
