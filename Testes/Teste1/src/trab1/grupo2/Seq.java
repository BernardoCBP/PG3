package trab1.grupo2;

public class Seq extends Transform {

    // VARIABLES
    protected Transform[] tfs;

    // CONSTRUCTOR
    public Seq( Transform ... tfs ) {
        super("sequence: " + getString(tfs, " >> "));
        this.tfs = tfs;
    }

    // METHODS
    @Override
    public String[] apply( String ... s ) throws TransformException {
        String[] aux = s;
        for( Transform tf : tfs ) {
            aux = tf.apply(aux);
        }
        return aux;
    }

}
