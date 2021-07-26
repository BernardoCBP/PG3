package trab2.grupo2;

import org.junit.jupiter.api.Test;

import trab2.grupo1.Name;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestFamilies {

    @Test
    public void testisOrdered()  {
        assertTrue( Families.isOrdered( Arrays.asList(95,80,54,35,26, 10), Integer::compareTo ) );
        assertTrue( Families.isOrdered( Arrays.asList(10,26,35,54,80, 95), Integer::compareTo ) );
        assertFalse( Families.isOrdered( Arrays.asList(95,80,85,35,105, 10), Integer::compareTo ) );
        assertFalse( Families.isOrdered( Arrays.asList(95,80,80,35,26, 10), Integer::compareTo ) );
    }

    @Test
    public void testFamilies() throws IOException {
        StringReader sr = new StringReader("Maria Manuela Torres Sousa\n" +
                "Maria Torres\n"+
                "Francisco Sousa Veiga\n" +
                "Luis Manuel Sousa\n");
        Map<String, TreeSet<Name>> map = Families.families(new BufferedReader(sr), TreeMap::new, TreeSet::new);
        assertEquals("{Sousa=[Sousa, Luis Manuel, Sousa, Maria Manuela Torres], Torres=[Torres, Maria], Veiga=[Veiga, Francisco Sousa]}", map.toString());
    }

    @Test
    public void testPrint() throws IOException {
        String[] resultFinal = {
                "Sousa 2",  "\tSousa, Luis Manuel", "\tSousa, Maria Manuela Torres",
                "Torres 1", "\tTorres, Maria",
                "Veiga 1",  "\tVeiga, Francisco Sousa"};
        StringReader source = new StringReader(
                "Maria Manuela Torres Sousa\n" +
                "Maria Torres\n"+
                "Francisco Sousa Veiga\n" +
                "Luis Manuel Sousa\n");
        Map<String, TreeSet<Name>> map = Families.families(new BufferedReader(source), TreeMap::new, TreeSet::new);
        StringWriter destination = new StringWriter();
        Families.printFamilies( new PrintWriter( destination ), map );
        BufferedReader copyOfDestination = new BufferedReader( new StringReader( destination.toString() ));
        for (String line : resultFinal )
            assertEquals( line, copyOfDestination.readLine() );
    }

 }
