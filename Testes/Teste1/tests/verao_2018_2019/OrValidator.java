package testes.test1;

/**
 * Created by msousa on 4/17/2019.
 */
public class OrValidator extends ComposeValidator {
    public OrValidator() {
        super("OR");
    }
    @Override
    public boolean validate(String s) {
        return countValidates( s ) != 0;
    }
    @Override
    public Validator or( Validator v ) {
        validators.add( v );
        return this;
    }

}
