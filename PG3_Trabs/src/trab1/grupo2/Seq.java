package trab1.grupo2;

public class Seq extends Transform{


    public Seq(Transform ... tfs) {
        super("sequence: " + getString(tfs, ">>"));

    }

    public String[] apply(String ... s) {
        return null;

    }
}
