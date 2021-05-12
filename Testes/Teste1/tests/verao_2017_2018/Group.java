package testes.test1;

import java.util.Scanner;

/**
 * Created by msousa on 4/23/2018.
 */
public class Group extends QueryCollection implements Query {
    public Group( String title ) {
        super( title );
    }
    public String getHeader() {
        return "[" + getPoints()+"] " + title;
    }
    public static void main(String[] args) throws QueryException{
        Group g1 = new Group("Object Oriented");
        g1.append(new IntQuest ("Quantas visibilidades podem ter os campos", 1, 4))
                .append(new IntQuest ("Qual o número de parâmetros do método toString", 2, 0));
        System.out.println( g1.getHeader() );
        Scanner in = new Scanner(System.in);
        System.out.println("-> " + g1.ask(in,""));    }
}
