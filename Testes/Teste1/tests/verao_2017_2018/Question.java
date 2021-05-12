package testes.test1;

import java.util.Scanner;

/**
 * Created by msousa on 4/23/2018.
 *
 */
public abstract class Question implements Query {
    private final int points;
    private final String inquiry;
    protected Question( String inquiry, int points) {
        this.points = points;
        this.inquiry = "[" + this.points + "] " + inquiry +"? ";
    }
    public final int getPoints() { return points;  }
    public String getInquiry()   { return inquiry; }
    public int ask(Scanner in, String prefix ) {
        System.out.print( prefix + getInquiry() );
        return readAnswer( in ) ? points : 0;
    }
    protected abstract boolean readAnswer(Scanner in);
}
