package trab2.grupo1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestName {
    @Test
    public void testConstruct() {
        Name n= new Name("Maria Manuela Torres Sousa");
        assertEquals("Sousa", n.getSurname());
        assertEquals("Maria Manuela Torres", n.getFirstNames());
    }

    @Test
    public void testSurname() {
        assertEquals("Sousa", new Name("Maria   Manuela  Sousa  ").getSurname());
        assertEquals("Sousa", new Name("Sousa").getSurname());
        assertEquals("Sousa", new Name("  Sousa  ").getSurname());
    }

    @Test
    public void testFirstNames() {
        assertEquals("Maria Manuela", new Name("  Maria   Manuela  Sousa  ").getFirstNames());
        assertEquals("Maria Manuela", new Name("Maria\t   Manuela \tSousa  ").getFirstNames());
        assertEquals("Manuela", new Name("   Manuela \tSousa  ").getFirstNames());
        assertEquals("", new Name("Sousa").getFirstNames());
        assertEquals("", new Name("  Sousa  ").getFirstNames());
    }

    @Test
    public void testFullName() {
        assertEquals("Maria Manuela Sousa", new Name("  Maria   Manuela  Sousa  ").getFullName());
        assertEquals("Maria Manuela Sousa", new Name("Maria\t   Manuela \tSousa  ").getFullName());
        assertEquals("Manuela Sousa", new Name("   Manuela \tSousa  ").getFullName());
        assertEquals("Sousa", new Name("Sousa").getFullName());
        assertEquals("Sousa", new Name("  Sousa  ").getFullName());
    }

    @Test
    public void testToString() {
        assertEquals("Sousa, Maria Manuela", new Name("  Maria   Manuela  Sousa  ").toString());
        assertEquals("Sousa, Maria Manuela", new Name("Maria\t   Manuela \tSousa  ").toString());
        assertEquals("Sousa, Manuela", new Name("   Manuela \tSousa  ").toString());
        assertEquals("Sousa", new Name("Sousa").toString());
        assertEquals("Sousa", new Name("  Sousa  ").toString());
    }

    @Test
    public void testEquals() {
        Name n = new Name("Maria Manuela Sousa");
        assertEquals( n, new Name("  Maria   Manuela  Sousa  "));
        assertTrue(n.equals(n));
        assertTrue(n.equals(new Name("Maria\t   Manuela \tSousa  ")));
        assertFalse(n.equals(new Name("Maria Sousa")));
        assertFalse(n.equals(new Name("Maria Manuela Sousa").toString()));
        assertFalse(n.equals( "Maria Manuela Sousa" ));
        assertFalse(n.equals( null ));
        assertFalse(n.equals( 10 ));
    }

    @Test
    public void testHashCode() {
        Name n = new Name("Maria Manuela Sousa");
        assertEquals( n.hashCode(), new Name("Maria Manuela Sousa").hashCode());
        assertEquals( n.hashCode(), new Name("  Maria   Manuela  Sousa  ").hashCode());
        assertTrue(n.hashCode() == new Name("  Maria\t   Manuela \tSousa  ").hashCode());
    }

}
