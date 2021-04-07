package trab1.grupo1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayer {
    private void testGetters(Player p, String n, int nq, int tp) {
        assertEquals(n, p.getName());
        assertEquals(nq, p.getNumQuizzes());
        assertEquals(tp, p.getTotalQuizPoints());
        assertEquals(tp / nq, p.getPoints());
    }

    @Test
    public void testGetters() {
        Player p = new Player("Manuela Sousa", 2, 20);
        testGetters(p, "Manuela Sousa", 2, 20);
    }

    @Test
    public void testToString() {
        Player p = new Player("Manuela Sousa", 2, 20);
        assertEquals("2:20 - Manuela Sousa", p.toString());
    }

    @Test
    public void testConstructorString() {
        Player p = new Player("5:47  - Manuel Torres");
        testGetters(p, "Manuel Torres", 5, 47);
    }

    @Test
    public void testEquals() {
        Player p1 = new Player("Manuela Sousa", 2, 20);
        assertFalse(p1.equals(null));
        assertTrue(p1.equals(p1));
        assertTrue(p1.equals(new Player("Manuela Sousa", 2, 20)));
        assertTrue(p1.equals(new Player(new String("Manuela Sousa"), 2, 20)));
        assertFalse(p1.equals(new Player("Manuela Sousa", 2, 25)));
        assertFalse(p1.equals(new Player("Manuela Sousa", 3, 20)));
        assertFalse(p1.equals(new Player("Manuel Sousa", 3, 20)));
        assertEquals(p1, new Player("Manuela Sousa", 2, 20));
    }

    @Test
    public void testCompareTo() {
        Player p1 = new Player("Manuela", 2, 20);
        Player p2 = new Player("Manuela", 2, 10);
        Player p3 = new Player("Manuela", 3, 60);
        assertEquals(0, p1.compareTo(p1));
        assertEquals(0, p1.compareTo(new Player("Manuel", 3, 31)));
        assertTrue(p1.compareTo(p2) > 0);
        assertTrue(p3.compareTo(p1) > 0);
        assertTrue(p1.compareTo(new Player("Manuela", 40, 80)) > 0);
        assertTrue(p2.compareTo(p1) < 0);
        assertTrue(p1.compareTo(p3) < 0);
    }

    @Test
    public void testAdd() {
        Player p = new Player("5:47 - Manuel Torres");
        p.addQuiz(15);
        testGetters(p, "Manuel Torres", 6, 47 + 15);
    }

    @Test
    public void testGetPlayer() {
        Player[] array = {
                new Player("aa", 1, 10),
                new Player("bb", 1, 15),
                new Player("cc", 1, 20),
                new Player("dd", 2, 56)
        };
        for (Player p: array) {
            assertEquals( p, Player.getPlayer( array,  p.getName()));
        }
        Player p = Player.getPlayer(array, "a" );
        assertEquals("a", p.getName());
        assertEquals(0, p.getPoints());
    }

    @Test
    public void testMinimum() {
        Player[] array = {
                new Player("aa", 1, 10),
                new Player("bb", 1, 15),
                new Player("cc", 1, 20)
        };
        assertNull( Player.minimum() );
        assertEquals(array[0], Player.minimum(array[0], array[1]));
        assertEquals(array[1], Player.minimum(array[2], array[1]));
        assertEquals(array[0], Player.minimum(array));
        assertEquals(array[0], Player.minimum(array[1], array[0], array[2]));
        assertEquals(array[0], Player.minimum(array[2], array[1], array[0]));
    }

    private void testTopN( Player[] array, int n ) {
        Collections.shuffle( Arrays.asList( array ) );
        Player[] res= Player.getTop10( array );
        Arrays.sort(array, (p1, p2)-> p2.compareTo(p1));
        assertArrayEquals(Arrays.copyOf( array, n ), res );
    }

    @Test
    public void testTop10() {
         Player[] array = {
                new Player("aa", 8, 80), // 10
                new Player("bb", 4, 60), // 15
                new Player("cc", 3, 62), // 20
                new Player("dd", 2, 50), // 25
                new Player("ee", 2, 60), // 30
                new Player("ff", 2, 70), // 35
                new Player("gg", 2, 80), // 40
                new Player("hh", 1, 50), // 50
                new Player("ii", 2, 108),// 54
                new Player("jj", 1, 56), // 56
                new Player("ll", 2, 116),// 58
                new Player("mm", 1, 60)  // 60
        };
        assertEquals(0, Player.getTop10( new Player[0] ).length);
        assertEquals( array[0], Player.getTop10( new Player[] {array[0]})[0]);
        assertArrayEquals( new Player[] {array[1], array[0]}, Player.getTop10( Arrays.copyOf(array, 2)));
        testTopN(Arrays.copyOf( array, 6), 6 );
        testTopN(array, 10 );
    }
}