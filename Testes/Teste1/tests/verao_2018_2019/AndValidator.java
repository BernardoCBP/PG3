package testes.test1;

import java.util.ArrayList;

/**
 * Created by msousa on 4/17/2019.
 */
public class AndValidator extends ComposeValidator {
    public AndValidator() { super("AND"); }
    @Override
    public boolean validate(String s) {
        return countValidates( s ) == validators.size();
    }
    @Override
    public Validator and(Validator v ) {
        validators.add( v );
        return this;
    }
}
