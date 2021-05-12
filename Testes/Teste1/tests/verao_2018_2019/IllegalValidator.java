package testes.test1;

/**
 * Created by msousa on 4/23/2019.
 */
public class IllegalValidator extends Exception {
    public IllegalValidator(String msg) {
        super("Illegal validator: " + msg );
    }
}
