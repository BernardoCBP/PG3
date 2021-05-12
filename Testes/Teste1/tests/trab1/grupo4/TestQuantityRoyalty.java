package trab1.grupo4;

import org.junit.jupiter.api.Test;
import trab1.grupo3.Artwork;
import trab1.grupo3.Author;
import trab1.grupo3.Song;

import java.rmi.UnexpectedException;

import static org.junit.jupiter.api.Assertions.*;

public class TestQuantityRoyalty {
    private static Author author = new Author() {
        public String getName() { return "Alcoolémia"; }
        public boolean equals( Object o ) {
            return o instanceof Author &&((Author)o).getName().equals("Alcoolémia"); }
    };
    private static Artwork aw = new Song(2014, "Palma da Mão", author, 200);
    private static Artwork aw2 = new Song(2012,"Grandes Feitos", author,  230);

    private void testParams(Royalty r,  Artwork aw, int v, boolean payed ) {
        assertEquals(v, r.getValue(  ));
        assertEquals(aw, r.getArtwork(  ));
        assertEquals(payed, r.isPayed(  ));
    }
    @Test
    public void testConstruct() {
        testParams( new QuantityRoyalty(aw, 5, 10), aw, 50, false);
        testParams( new QuantityRoyalty(aw2, 6, 12), aw2, 72, false);
    }

    @Test
    public void testToString() {
        assertEquals("€50 --> Palma da Mão (Alcoolémia) [03:20]", new QuantityRoyalty(aw, 5, 10).toString());
        assertEquals("€72 --> Grandes Feitos (Alcoolémia) [03:50]", new QuantityRoyalty(aw2, 6, 12).toString());
    }

    @Test
    public void testEquals() {
        Royalty  r1 = new QuantityRoyalty(aw, 5, 10);
        Royalty r2 = new QuantityRoyalty(new Song(2014, "Palma da Mão", author, 200), 5, 10);
        assertTrue(r1.equals( r1 ));
        assertTrue(r1.equals( r2 ));
        assertEquals(r1, r2);
        assertFalse( r1.equals(  new QuantityRoyalty(aw2, 6, 12)));
        assertFalse( r1.equals(  null ) );
   }

    @Test
    public void testPay() {
       try {
           Royalty r = new QuantityRoyalty(aw, 5, 10);
           assertFalse(r.isPayed());
           r.pay();
           assertTrue(r.isPayed());
       } catch (RoyaltyException e) {
           fail("Unexpected Exception", e);
       }
   }

    @Test
    public void testPayException() {
       Royalty r = new QuantityRoyalty(aw, 5, 10);

       RoyaltyException ex = assertThrows(
               RoyaltyException.class,
               () -> { r.pay(); r.pay(); }
       );
       assertEquals("One royalty cannot be payed twice", ex.getMessage());
   }
}
