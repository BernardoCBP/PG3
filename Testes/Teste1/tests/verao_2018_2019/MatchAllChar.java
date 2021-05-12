package testes.test1;

import java.util.function.Predicate;

/**
 * Created by msousa on 4/17/2019.
 */
public class MatchAllChar extends Validator {
    public final CharPredicate pred;

    public MatchAllChar(String desc, CharPredicate pred) {
        super(desc);
        this.pred = pred;
    }

    @Override
    public boolean validate(String s) {
        for ( int i= 0; i < s.length(); ++i )
          if (!pred.test(s.charAt( i)))
              return false;
        return true;
    }
}
