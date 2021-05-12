package testes.test1;

/**
 * Created by msousa on 4/17/2019.
 */
public class LenValidator extends Validator {
    private final int minimum;
    private final int maximun;

    private LenValidator(String desc, int min, int max ) throws IllegalValidator {
      super( desc );
      if ( min < 0 || max < 0 )
          throw new IllegalValidator( "invalid length");
      minimum = min;
      maximun = max;
    }
    public LenValidator(int min, int max ) throws IllegalValidator {
        this("length in [" + min + ", " + max + ']', min, max);
    }
    public LenValidator( int max ) throws IllegalValidator{
        this("length less than " + max, 0, max );
    }
    @Override
    public boolean validate(String s) {
        return s.length() >= minimum && s.length() <= maximun;
    }
}
