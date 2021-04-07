package trab1.grupo3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSong {
    private static class AuthorTest implements Author{
        private final String name;

        AuthorTest(String n) { name = n; }
        public String getName() { return name; }
        public boolean equals( Object o ) {
            return o instanceof Author && ((Author)o).getName().equals(name); }
    };
    private static Author author = new AuthorTest("Alcoolémia");

    @Test
    public void testToString() {
        Song s1 = new Song(2012, "Mil Uns de Abril", author, 230);
        assertEquals("Mil Uns de Abril (Alcoolémia) [03:50]", s1.toString());
        Song s2 = new Song(2013,"Ponto de Fuga", author,  189);
        assertEquals("Ponto de Fuga (Alcoolémia) [03:09]", s2.toString());
    }

    @Test
    public void testAuthor() {
        assertEquals( "Alcoolémia", author.getName());
        assertEquals( author, new AuthorTest("Alcoolémia"));
        Song s = new Song(2012, "Mil Uns de Abril", author, 230);
        assertEquals(author, s.getAuthor() );
     }

    @Test
    public void testEquals() {
        Song s1 = new Song(2012, "Mil Uns de Abril", author, 230);
        Song s2 = new Song(2012, "Mil Uns de Abril", author, 230);
        assertTrue(s1.equals( s1 ));
        assertTrue(s1.equals( s2 ));
        assertEquals(s1, s2);
        assertTrue(s1.equals( new Song(2012, new String("Mil Uns de Abril"), author, 230)));
        assertTrue(s1.equals( new Song(2012, new String("Mil Uns de Abril"), author, 202)));
        assertFalse( s1.equals( new Song(2013, "Mil Uns de Abril", author, 230)));
        assertFalse( s1.equals( new Song(2012,"Grandes Feitos", author,  230)));
        assertFalse( s1.equals( new Song(2012, "Mil Uns de Abril", new AuthorTest("outro"), 230)));
        assertFalse( s1.equals(  null ) );
   }

    @Test
    public void testGetMatch() {
        Song s1 = new Song(2012, "Mil Uns de Abril", author, 230);
        assertEquals(s1, s1.getMatch( s1 ));
        assertEquals(s1, s1.getMatch( new Song(2012, "Mil Uns de Abril", author, 230)));
        assertNull( s1.getMatch(  new Song(2012, "Mil Uns de Abril", new AuthorTest("outro"), 230)));
        assertNull( s1.getMatch(  new Song(2013,"Mil Uns de Abril", author,  201) ));
        assertNull( s1.getMatch(  new Song(2013,"Ponto de Fuga", author,  201) ));
        assertNull( s1.getMatch( null ));
    }

    @Test
    public void testGetSeconds() {
        Song s= new Song(2012, "Mil Uns de Abril", author, 230);
        assertEquals(230, s.getSeconds());
    }

    @Test
    public void testGetArtwork() {
        Song[] songs = {
                new Song(2012, "Mil Uns de Abril", author, 230),
                new Song(2014, "Palma da Mão", author, 230),
                new Song(2013, "Forasteiro Gaibéu", author, 239),
                new Song(2013, "Ponto de Fuga", author, 201),
                new Song(2013, "Leva-Me Onde Quiseres", author, 193),
                new Song(2012, "Grandes Feitos", author, 202),
                new Song(2014, "Alma Rock", author, 214),
                new Song(2014, "Derrotas da Paixão", author, 302),
                new Song(2010, "P.A.I.", author, 219)
        };
        assertNull( Artwork.getArtwork(songs[0], new Song[0]) );
        assertEquals( songs[0], Artwork.getArtwork(songs[0], songs[0]) );
        assertEquals( songs[0], Artwork.getArtwork(songs[0], songs[0], songs[1]) );
        assertEquals( songs[0], Artwork.getArtwork(songs[0], songs[1], songs[0]) );
        for ( Song s: songs )
            assertEquals( s, Artwork.getArtwork( s, songs ));
        assertNull( Artwork.getArtwork(new Song(2015, "Mil Uns de Abril", author, 230), songs) );
        assertNull( Artwork.getArtwork(songs[1], songs[0]) );
        assertNull( Artwork.getArtwork(songs[2], songs[1], songs[0]) );
    }

    /*
    @Test
    public void testException() {
        ArtworkException ex = assertThrows(
                ArtworkException.class,
                () -> new Song(2012, "Mil Uns de Abril", author, -10)
        );
        assertEquals("Duration must be greater than 0", ex.getMessage());
    }
    */
}
