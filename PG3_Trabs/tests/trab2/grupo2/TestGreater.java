package trab2.grupo2;

import org.junit.jupiter.api.Test;
import trab2.grupo1.Name;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGreater {
    @Test
    public void testEmptyGreater() throws IOException {
        assertEquals(0, Families.greaterFamilies( Collections.emptyMap() ).size());
    }
    @Test
    public void testOneElementsGreater() throws IOException {
        StringReader sr = new StringReader("Maria Manuela Torres Sousa\n" );
        TreeSet<Name> value = new TreeSet<>(Arrays.asList( new Name("Veiga")));
        Map<String, TreeSet<Name>> map = Collections.singletonMap("Veiga", value );
        assertEquals("[Veiga]", Families.greaterFamilies( map ).toString());
    }

    @Test
    public void testOnlyOneGreater() throws IOException {
        StringReader sr = new StringReader("Maria Manuela Torres Sousa\n" +
                "Maria Torres\n" +
                "Francisco Sousa Veiga\n" +
                "Luis Manuel Sousa\n");
        Map<String, TreeSet<Name>> map = Families.families(new BufferedReader(sr), TreeMap::new, TreeSet::new);
        assertEquals("[Sousa]", Families.greaterFamilies(map).toString());
    }
    @Test
    public void testTwoGreater() throws IOException {
        StringReader sr = new StringReader("Maria Manuela Torres Sousa\n" +
                "Maria Torres\n"+
                "Manuel Torres\n"+
                "Francisco Sousa Veiga\n" +
                "Luis Manuel Sousa\n");
        Map<String, TreeSet<Name>> map = Families.families(new BufferedReader(sr), TreeMap::new, TreeSet::new);
        assertEquals("[Torres, Sousa]", Families.greaterFamilies( map ).toString());
    }


}
