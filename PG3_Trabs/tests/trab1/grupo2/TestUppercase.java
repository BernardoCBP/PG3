package trab1.grupo2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUppercase {
    @Test
    public void testToString() {
        UpperCase t = new UpperCase();
        assertEquals("to upper", t.toString());
    }

    @Test
    public void testApply() {
        UpperCase t = new UpperCase();
        assertEquals("PGII SEMESTRE DE VERAO  2020\\2021", t.apply("PGII semestre de Verao  2020\\2021")[0]);
        String[] array= { "", "PGI", "PGII", "PGIII", "SO", "AL"};
        assertArrayEquals(array, t.apply("", "PgI", "PgII", "PgIII", "So", "al"));
    }
}
