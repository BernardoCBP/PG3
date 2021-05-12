package testes.test1;

/**
 * Created by msousa on 4/17/2019.
 */
public class NotValidator extends Validator {
    public final Validator validator;
    public NotValidator( Validator v ) {
        super("not " + v );
        validator = v;
    }
    @Override
    public boolean validate(String s) {
        return !validator.validate( s );
    }
    @Override
    public Validator negate(){
        return  validator;
    }
}
