package testes.test1;

/**
 * Created by msousa on 4/17/2019.
 */
public abstract class Validator {
    private final String description;
    public Validator( String desc ){
        description = desc;
    }
    public abstract boolean validate( String s );
    @Override
    public String toString() { return description; }

    public Validator negate() {
        return new NotValidator( this );
    }
    public Validator and( Validator v ) { return new AndValidator( ).and(this).and( v ); }
    public Validator or( Validator v ) {return new OrValidator( ).or(this).or( v ); }

    public void report( String s ){ System.out.println(s + " - " + toString() + "? "+ validate(s));}
}
