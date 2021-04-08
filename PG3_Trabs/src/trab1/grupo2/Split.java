package trab1.grupo2;

import java.util.Arrays;

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

        //String sep = checkSeparator();

        String[] res = new String[0];
        int size = 0;

        for (String value : s) {
            String[] aux = value.split("\\" + this.separator);

            size += aux.length;
            res = Arrays.copyOf(res, size);
            System.arraycopy(aux, 0, res, size - aux.length, aux.length);
        }
        return res;
    }
/*
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
*/
}