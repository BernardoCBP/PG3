package testes.test1;

/**
 * Created by msousa on 4/24/2019.
 */
public class Examples {
    public static void exampleLen() {
        try {
            Validator len = new LenValidator(3, 5);
            len.report( "123" ); len.report( "12" );
            len.report( "12345" ); len.report( "123456" );

            Validator lenMax = new LenValidator(4);
            lenMax.report( "123" ); lenMax.report( "12" );
            lenMax.report( "12345" ); lenMax.report( "123456" );

            Validator illegal = new LenValidator(1, -1);
        } catch (IllegalValidator e) {
            System.out.println(e.getMessage());
        }
    }
    public static void exampleMatch() {
        Validator num = new MatchAllChar("number", c -> Character.isDigit( c) );
        num.report( "123" );
        num.report( "1.2" );
    }

    public static void exampleNot() {
        try {
        Validator not = new NotValidator( new LenValidator(4) ) ;
        not.report(  "12345");
        not.report(  "1234");

        Validator len = not.negate();
        len.report(  "123");
        len.report(  "123456");
        }
        catch (IllegalValidator e ) {
            System.out.println("- BUG - ");
        }
    }
    public static void exampleAnd() {
        try {
            Validator name = new MatchAllChar ("name", c -> Character.isLetter( c ) );
            Validator len = new LenValidator(4, 10);

            Validator v = new AndValidator().and(name).and(len);
            v.report(  "Maria");
            v.report(  "Ana");
            v.report( "Ana Maria");


            name = new MatchAllChar ("name", c -> Character.isLetter( c ) || c == ' ');
            len = new LenValidator(3, 10);

            v = new AndValidator().and(name).and(len);
            v.report(  "Maria");
            v.report(  "Ana");
            v.report( "Ana Maria");
        }
        catch (IllegalValidator e ) {
            System.out.println("- BUG - ");
        }
    }

    public static void exampleOr() {
        Validator name = new MatchAllChar("name", c -> Character.isLetter(c));
        Validator num = new MatchAllChar("number", c -> Character.isDigit(c));

        Validator v = name.or(num);
        v.report("Maria");
        v.report("123");
        v.report("Maria2");
   }

    public static void main(String[] args) {
        exampleLen();
        exampleMatch();
        exampleNot();
        exampleAnd();
        exampleOr();
    }
}
