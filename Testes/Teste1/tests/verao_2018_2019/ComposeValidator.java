package testes.test1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msousa on 4/17/2019.
 */
public abstract class ComposeValidator extends Validator {
    protected final ArrayList<Validator> validators = new ArrayList<>();
    public ComposeValidator( String sep ) {
        super(sep);
    }

    protected int countValidates(String s) {
        int c=0;
        for( Validator v: validators )
            if ( v.validate(s) )
                ++c;
        return c;
    }
    @Override
    public String toString( ) {
        if ( validators.isEmpty()  ) return super.toString();
        StringBuilder sb = new StringBuilder( );
        for ( Validator v : validators )
            sb.append(' ').append( v ).append(' '). append( super.toString() );
        sb.delete(sb.length()-super.toString().length()-1, sb.length());
        return sb.toString();
    }
    public List<Validator> getValidators() { return validators; }
}
