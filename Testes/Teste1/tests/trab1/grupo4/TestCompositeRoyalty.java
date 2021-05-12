package trab1.grupo4;

import org.junit.jupiter.api.Test;
import trab1.grupo3.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class TestCompositeRoyalty {
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
            testParams(new CompositeRoyalty( aw ), aw, 0, false);
            testParams(new CompositeRoyalty( aw2 ), aw2, 0, false);
    }

    @Test
    public void testAppend() {
        CompositeRoyalty c = new CompositeRoyalty( aw );
        try {
            c.append( new QuantityRoyalty(aw, 5, 10));
            testParams(c, aw, 50, false);
            c.append( new QuantityRoyalty(aw, 6, 9)).append( new QuantityRoyalty(aw, 10, 2));
            testParams( c, aw, 124, false);

        } catch (RoyaltyException e) {
            throw new RuntimeException("Unexpected Exception", e);
        }
    }

    @Test
    public void testToString() {
        CompositeRoyalty c = new CompositeRoyalty( aw );
        assertEquals("(MULTI)€0 --> Palma da Mão (Alcoolémia) [03:20]", c.toString());
        try {
            c.append( new QuantityRoyalty(aw, 5, 10));
        } catch (RoyaltyException e) {
            throw new RuntimeException("Unexpected Exception", e);
        }
        assertEquals("(MULTI)€50 --> Palma da Mão (Alcoolémia) [03:20]", c.toString());
    }

    @Test
    public void testPay() {
        try {
            CompositeRoyalty c = new CompositeRoyalty(aw);
            c.append( new QuantityRoyalty(aw, 6, 9)).append( new QuantityRoyalty(aw, 10, 2));
            assertFalse(c.isPayed());
            c.pay();
            assertTrue(c.isPayed());
            c.append(new QuantityRoyalty(aw, 6, 12));
            assertFalse(c.isPayed());
        } catch (RoyaltyException e) {
            throw new RuntimeException("Unexpected Exception", e);
        }
    }

    @Test
    public void testGetRoyalties() {
        try {
            Royalty[] array = {
                    new QuantityRoyalty(aw, 6, 9),
                    new QuantityRoyalty(aw, 5, 10),
                    new QuantityRoyalty(aw, 6, 12)
            };
            CompositeRoyalty c = new CompositeRoyalty(aw);
            c.append( array[0]).append( array[1] ).pay();
            testParams(c, aw, 104, true);

            testResultRoyalties(c, r -> true, 2, array[0], array[1]);
            c.append( array[2] );
            testResultRoyalties(c, r -> true, 3, array);
            testResultRoyalties(c, r -> !r.isPayed(), 1, array[2]);
            testResultRoyalties(c, r -> r.getValue() > 50, 2, array[0], array[2]);
            testResultRoyalties(c, r -> r.getValue() <= 50, 1, array[1]);

            assertTrue( c.getRoyalties( (r)-> r.getValue() > 100 ).isEmpty());

         } catch (RoyaltyException e) {
            fail("Unexpected Exception", e);
        }
    }

    private void testResultRoyalties(CompositeRoyalty c, Predicate<Royalty> filter, int sz, Royalty ... array ) {
        List<Royalty> res= c.getRoyalties( filter );
        assertEquals(sz,  res.size());
        assertEquals(Arrays.asList(array), res );
    }

    @Test
    public void testRemoveIf() {
        try {
            CompositeRoyalty c = new CompositeRoyalty(aw);
            c.append( new QuantityRoyalty(aw, 6, 9)).append( new QuantityRoyalty(aw, 5, 10));
            testParams(c, aw, 104, false);
            c.pay();
            testParams(c, aw, 104, true);
            c.append(new QuantityRoyalty(aw, 6, 12));
            testParams(c, aw, 176, false);
            assertEquals(2, c.removePayed());
            testParams(c, aw, 72, false);

        } catch (RoyaltyException e) {
            throw new RuntimeException("Unexpected Exception", e);
        }
    }


    @Test
    public void testAppendException() {
        CompositeRoyalty c = new CompositeRoyalty( aw );
        RoyaltyException ex = assertThrows(
                RoyaltyException.class,
                () -> c.append( new QuantityRoyalty(aw2, 5, 10)));

        assertEquals("Royalties artwork must match!", ex.getMessage());
    }

}
