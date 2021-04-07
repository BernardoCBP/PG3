package trab1.grupo2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSplit {
    @Test
    public void testToString() {
        Split t = new Split(' ');
        assertEquals("split", t.toString());
    }

    @Test
    public void testApply() {
        Split t = new Split(' ');
        assertArrayEquals(new String[]{"PGIII", "semestre", "de", "Verao", "", "2020\\2021"},
                          t.apply("PGIII semestre de Verao  2020\\2021"));
        t = new Split(',');
        String[] array= { "", "PGI"," PGII"," PGIII", "AL"," MI"," M2", "SO"};
        assertArrayEquals(array, t.apply("", "PGI, PGII, PGIII", "AL, MI, M2", "SO"));
    }
}
