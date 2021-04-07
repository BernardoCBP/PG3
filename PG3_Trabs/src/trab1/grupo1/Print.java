package trab1.grupo1;

public class Print {
    public static void main(String[] args) {
        Player p1 = new Player ("aa", 1, 10 );
        System.out.println( p1.toString() );
        Player p2 = new Player ("1:0 - aa");
        System.out.println( p1 == p2 );
        System.out.println( p1.equals(p2) );
        Player p3 = p1;
        System.out.println( p1.equals(p3) );
        if ( p3 != null )
            System.out.println( p1 == p3 );
        /*
        System.out.println(p1);
        System.out.println( p2.getName() + " " + p2.getNumQuizzes() + " " + p2.getTotalPoints());
        System.out.println("Points:" + p1.compareTo(p2));
        */
        Player[] array = {
                new Player("aa", 1, 10),
                new Player("bb", 1, 1),
                new Player("cc", 1, 5),
                new Player("dd", 1, 56)
        };

        Player p4 = Player.getPlayer(array, "Juan" );
        System.out.println(p4);

        System.out.println("Min: " + Player.minimum(array[0], array[1], array[2], array[3]));
    }
}
