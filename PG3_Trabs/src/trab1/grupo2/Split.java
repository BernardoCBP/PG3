package trab1.grupo2;

public class Split extends Transform {

    // VARIABLES
    private final char separator;

    // CONSTRUCTOR
    public Split( char separator ) {
        super("split");
        this.separator = separator;
    }

    // METHODS
    @Override
    public String[] apply( String ... s ) {
        String sep = checkSeparator();

        int size = 0;
        for( int i=0; i < s.length; i++ ) {
            size += s[i].split(sep).length;
        }

        String[] res = new String[size];
        // FAZER COM Array.copyOf() e APENAS um for

        size = 0;
        for( int i=0; i < s.length; i++ ) {
            String[] aux = s[i].split(sep);
            System.arraycopy( aux, 0, res, size, aux.length );
            size += aux.length;
        }

        return res;
    }

    private String checkSeparator() {
        String sep = String.valueOf(this.separator);
        if(sep.equals("\\") || sep.equals("^") || sep.equals("$") || sep.equals(".") ||
                sep.equals("|") || sep.equals("?") || sep.equals("*") || sep.equals("+") ||
                sep.equals("(") || sep.equals(")") || sep.equals("[") || sep.equals("]") ||
                sep.equals("{") || sep.equals("}") || sep.equals("<") || sep.equals(">") ||
                sep.equals("-") || sep.equals("=") || sep.equals("!")
        ) {
            return "\\" + sep;
        }
        return sep;
    }

}