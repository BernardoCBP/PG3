package trab1.grupo3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestAlbum {
    private static class AuthorTest implements Author{
        String name;

        AuthorTest(String n) { name = n; }
        public String getName() { return name; }
        public boolean equals( Object o ) {
            return o instanceof Author && ((Author)o).getName().equals(name); }
    };
    private static Author author = new AuthorTest("Alcoolémia");

    private static Song milUnsAbril =new Song(2012, "Mil Uns de Abril", author, 230);
    private static Song almaRock =new Song(2014, "Alma Rock", author, 214);
    private static Song homemLeme =new Song(2004, "Homem do Leme", new AuthorTest("Xutos & Pontapés"), 192);
    private static Song[] songs = {
            milUnsAbril,
            new Song(2014, "Palma da Mão", author, 231),
            new Song(2013, "Forasteiro Gaibéu", author, 240),
            new Song(2013, "Ponto de Fuga", author, 202),
            new Song(2013, "Leva-Me Onde Quiseres", author, 193),
            new Song(2012, "Grandes Feitos", author, 202),
            new Song(2014, "Alma Rock", author, 214),
            new Song(2014, "Derrotas da Paixão", author, 303),
            new Song(2010, "P.A.I.", author, 220)};

    private static Album album = new Album ( "Palma da Mão", author, songs);
    @Test
    public void testToString() {
       String s =   "2014 - Palma da Mão (Alcoolémia)\n" +
                     "\t1 - P.A.I. (Alcoolémia) [03:40]\n" +
                     "\t2 - Grandes Feitos (Alcoolémia) [03:22]\n" +
                     "\t3 - Mil Uns de Abril (Alcoolémia) [03:50]\n" +
                     "\t4 - Forasteiro Gaibéu (Alcoolémia) [04:00]\n" +
                     "\t5 - Leva-Me Onde Quiseres (Alcoolémia) [03:13]\n" +
                     "\t6 - Ponto de Fuga (Alcoolémia) [03:22]\n" +
                     "\t7 - Alma Rock (Alcoolémia) [03:34]\n" +
                     "\t8 - Derrotas da Paixão (Alcoolémia) [05:03]\n" +
                     "\t9 - Palma da Mão (Alcoolémia) [03:51]\n";
       assertEquals(s, album.toString());
       System.out.println(album.toString());
    }

    @Test
    public void print() {
        System.out.println(album.toString());
    }

    @Test
    public void testAuthor() {
        assertEquals(author, album.getAuthor() );
    }

    @Test
    public void testEquals() {
            Song copy =new Song(2012, "Mil Uns de Abril", author, 230);
            assertTrue(milUnsAbril.equals( milUnsAbril ));
            assertTrue(milUnsAbril.equals( copy ));
            assertEquals(milUnsAbril, copy);
            assertTrue(milUnsAbril.equals( new Song(2012, new String("Mil Uns de Abril"), author, 230)));
            assertTrue(milUnsAbril.equals( new Song(2012, new String("Mil Uns de Abril"), author, 202)));
            assertFalse( milUnsAbril.equals( new Song(2013, "Mil Uns de Abril", author, 230)));
            assertFalse( milUnsAbril.equals( new Song(2012,"Grandes Feitos", author,  230)));
            assertFalse( milUnsAbril.equals( new Song(2012, "Mil Uns de Abril", new AuthorTest("outro"), 230)));
            assertFalse( milUnsAbril.equals(  null ) );
    }

    @Test
    public void testGetMatch() {
            for (Song s : album.getSongs())
                assertEquals(s, album.getMatch(s));
            assertNull(album.getMatch(new Song(2012, "Mil Uns de Abril", new AuthorTest("outro"), 230)));
            assertNull(album.getMatch(new Song(2015, "Ponto de Fuga", author, 201)));
            assertNull(album.getMatch(null));
    }
    @Test
    public void testYear() {
        assertEquals(2014, album.year);
    }

    @Test
    public void testGetArtwork() {
        for (Song s : album.getSongs())
            assertEquals(s, album.getArtwork(s, milUnsAbril, album, homemLeme));
        assertEquals(milUnsAbril, album.getArtwork(milUnsAbril, milUnsAbril, homemLeme, album));
        assertEquals(homemLeme, album.getArtwork(homemLeme, milUnsAbril, album, homemLeme));
        assertNull(album.getArtwork(homemLeme, milUnsAbril, album));

    }
    @Test
    public void testGetLatest() {
        assertNull( Album.getLatest() );
        assertNull( Album.getLatest(null) );
        assertEquals( homemLeme, Album.getLatest( homemLeme));
        assertEquals( milUnsAbril, Album.getLatest( homemLeme, milUnsAbril));
        assertEquals( almaRock, Album.getLatest( homemLeme, almaRock, milUnsAbril));
        assertEquals( almaRock, Album.getLatest( almaRock, homemLeme, milUnsAbril));
        assertEquals( 2014, Album.getLatest( songs ).year);
    }


    @Test
    public void testException() {
        ArtworkException ex = assertThrows(
                ArtworkException.class,
                () -> new Album("Palma da Mão", author, songs[0])
        );
        assertEquals("The album must have at least two songs", ex.getMessage());
    }

}
