package trab1.grupo2;

public abstract class SingleTransform extends Transform {

    // CONSTRUCTOR
    public SingleTransform( String nm ) {
        super(nm);                          // the name is passed to the parent class constructor
    }

    // METHODS
    @Override
    public String[] apply( String ... s ) {
        String[] sArr = new String[s.length];

        for( int i = 0; i < s.length; i++ ) {
            sArr[i] = applyForEach( s[i] );         // apply the transform to each individual String
        }
        return sArr;
    }

    protected abstract String applyForEach( String s );
}
