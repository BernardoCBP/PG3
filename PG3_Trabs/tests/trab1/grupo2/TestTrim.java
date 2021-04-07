package trab1.grupo2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTrim {
    @Test
    public void testToString() {
        Trim t = new Trim();
        assertEquals("trim", t.toString());
    }

    @Test
    public void testApply() {
        Trim t = new Trim();
        assertEquals("PGII \n\t\tsemestre de Verao   2020\\2021", t.apply("   \tPGII \n\t\tsemestre de Verao   2020\\2021   \n")[0]);
        String[] array= { "", "PgI", "PgII", "PgIII"};
        assertArrayEquals(array, t.apply("  ", "\tPgI ", "  PgII ", "    PgIII\n"));
    }
}
