package trab1.grupo2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestJoin {
    @Test
    public void testToString() {
        JoinPairs t = new JoinPairs("-");
        assertEquals("join pairs with \"-\"", t.toString());
    }

    @Test
    public void testApply() {
        JoinPairs t = new JoinPairs("-");
        try {
            String[] res = t.apply("PGI", "PGII");
            assertEquals(1, res.length);
            assertEquals("PGI-PGII", res[0]);
            t = new JoinPairs(" -> ");
            assertArrayEquals(new String[]{"PGI -> PGII", "PGII -> PGIII"}, t.apply("PGI", "PGII", "PGIII"));
            t = new JoinPairs(" <-> ");
            assertArrayEquals(new String[]{"AL <-> M1", "M1 <-> M2", "M2 <-> PE"}, t.apply("AL", "M1", "M2", "PE"));
        } catch (Exception e) {
            fail("Unexpected Exception", e);
        }
    }

    /*
    @Test
    public void testException() {
        JoinPairs t = new JoinPairs("-");
        TransformException ex = assertThrows(
                TransformException.class,
                () ->  t.apply("PGIII")
        );
        assertEquals(t + " " + "no pairs found", ex.getMessage());
        assertTrue(t == ex.getTransform() );
    }
    */
}
