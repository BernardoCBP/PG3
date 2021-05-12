package testes.test1;

import java.util.Scanner;

/**
 * Created by msousa on 4/23/2018.
 *
 */
public class Quiz extends QueryCollection {
    public Quiz() { super("Questionário sobre JAVA"); }

    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Resultado -> " + ask(in, "") + " pontos");
   }

    public String getHeader() {
        return "== " + title + " == [" + getPoints()+" pontos]";
    }

    public boolean hasGroup(String t) {
        return find((q) -> q instanceof Group &&
                           t.equals(((Group) q).title)) != null;
    }


    public static void main(String[] args) throws QueryException {
        Question q1 = new IntQuest ("Quantos são os tipos primitivos", 3, 8);
        Question q2 = new MultQuest("Qual o espaço ocupado por uma variável do tipo int",
                2,"2 bytes","8 bits","32 bits","8 bytes");
        Group g1 = new Group("Object Oriented");
        g1.append(new IntQuest ("Quantas visibilidades podem ter os campos", 1, 4))
                .append(new IntQuest ("Qual o número de parâmetros do método toString", 2, 0));
        Quiz q = new Quiz( );
        q.append(q1).append(q2).append(g1);
        System.out.println( q.title );
        System.out.println( q.getHeader() );
        q.run();
    }

}
