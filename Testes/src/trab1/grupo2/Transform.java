package trab1.grupo2;

public abstract class Transform {

    // VARIABLES
    private final String name;

    // CONSTRUCTOR
    protected Transform( String nm ) {
        this.name = nm;
    }

    // METHODS
    public abstract String[] apply( String ... s ) throws TransformException;

    @Override
    public String toString() {
        return this.name;
    }

    public static String getString( Transform[] tfs, String sep ) {
        StringBuilder catString = new StringBuilder();
        int i;
        for( i=0; i < tfs.length-1; i++ ) {
            catString.append(tfs[i].toString()).append(sep);         // concatenates the name of the transform with
        }                                                            // the separator
        catString.append(tfs[i].toString());                         // the last elem doesnt concatenate with the sep
        return catString.toString();                                 // this returns the concatenated String
    }

}
