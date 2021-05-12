package trab1.grupo2;

public class JoinPairs extends Transform {

    // VARIABLES
    private final String separator;

    // CONSTRUCTOR
    public JoinPairs( String sep ) {
        super("join pairs with \"" + sep + "\"");
        this.separator = sep;
    }

    // METHODS
    @Override
    public String[] apply( String ... s ) throws TransformException {
        if( s.length < 2 ) {
            throw new TransformException( this, "no pairs found" );
        }

        String[] sArr = new String[s.length-1];         // there will be (s.length-1) pairs

        for( int i=0; i < s.length-1; i++ ) {
            sArr[i] = s[i] + this.separator + s[i+1];   // concatenates the two strings (with the separator between)
        }                                               // and saves the pair to (i) elem of the array
        return sArr;
    }

}
