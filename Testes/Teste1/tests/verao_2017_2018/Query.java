package testes.test1;

import java.util.Scanner;

/**
 * Created by msousa on 4/23/2018.
 *
 */
public interface Query {
    int ask(Scanner in, String prefix);
    int getPoints();
}
