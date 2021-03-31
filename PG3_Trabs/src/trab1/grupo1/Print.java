package trab1.grupo1;

public class Print {
    public static void main(String[] args) {
        Player p1 = new Player ("aa", 1, 10 );
        System.out.println( p1.toString() );
        Player p2 = new Player ("1:10 - aa");
        System.out.println( p1 == p2 );
        System.out.println( p1.equals(p2) );
        Player p3 = p1;
        System.out.println( p1.equals(p3) );
        if ( p3 != null )
            System.out.println( p1 == p3 );

        System.out.println(p1);
        System.out.println( p2.getName() + " " + p2.getNumQuizzes() + " " + p2.getTotalPoints());
        System.out.println("Points:" + p2.getPoints());
    }
}
