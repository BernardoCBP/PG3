package trab1.grupo2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSeq {
    private static Seq seq;
    private static Seq getSeq() {
        if ( seq == null ) {
            Transform split = new Split('|');
            Transform trim = new Trim();
            Transform join = new JoinPairs(" -> ");
            seq = new Seq(split, trim, join, new UpperCase());
        }
        return seq;
    }

    @Test
    public void testToString() {
            assertEquals("sequence: split >> trim >> join pairs with \" -> \" >> to upper", getSeq().toString());
     }

    @Test
    public void testApply() {
        try {
             assertArrayEquals(new String[]{"PGI -> PGII", "PGII -> SO", "SO -> PGIII"},
                               getSeq().apply("PgI | PgII | SO | pgIII"));
        } catch (Exception e) {
            fail("Unexpected Exception", e);
        }
    }
}
