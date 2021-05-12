package testes.test1;

import java.util.Scanner;

/**
 * Created by msousa on 4/23/2018.
 */
public class IntQuest extends Question {
    private final int resp;
    public IntQuest( String i, int p, int r) {
        super(i, p);
        resp = r;
    }

    @Override
    protected boolean readAnswer(Scanner in ) {
        return in.nextInt() == resp;
    }

    public static void main(String[] args) {
        Question q= new IntQuest ("Quantos são os tipos primitivos", 3, 8 );
        System.out.println( q.getInquiry() );
        Scanner in= new Scanner( System.in );
        System.out.println("Resultado -> "+q.ask(in,"1-")+" pontos");

    }
}
