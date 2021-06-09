package trab2.grupo1;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestForEachIf {
    @Test
    public void testOneName() throws IOException {
        StringReader sr = new StringReader("Maria Manuela Torres Sousa");
        StringBuilder sb = new StringBuilder();
        Function<String, String> f= line-> new Name(line).getSurname();
        Predicate<String> p = surname -> surname.equals("Sousa");
        BiConsumer<String, Integer> c= (surname, order) -> sb.append(order + " - " + surname);
        Names.forEachIf(new BufferedReader(sr), f, p, c );
        assertEquals("1 - Sousa", sb.toString());
    }

    @Test
    public void testNames() throws IOException {
        StringReader sr = new StringReader("Maria Manuela Torres Sousa\n" +
                                             "Maria Torres\n"+
                                             "Francisco Sousa Veiga\n" +
                                             "Luis Manuel Sousa\n");
        StringBuilder sb = new StringBuilder();
        Function<String, String> f= line-> new Name(line).toString();
        Predicate<String> p = formattedName-> formattedName.startsWith("Sousa,");
        BiConsumer<String, Integer> c= (formattedName, order) -> sb.append(sb.isEmpty()?"":"; ").append(order).append('-').append(formattedName);
        Names.forEachIf(new BufferedReader(sr), f, p, c );
        assertEquals("1-Sousa, Maria Manuela Torres; 2-Sousa, Luis Manuel", sb.toString());
    }
    @Test
    public void testAllNames() throws IOException {
        StringReader sr = new StringReader("Maria Manuela Torres Sousa\n" +
                "Francisco Sousa Veiga\n" +
                "Maria Torres\n"+
                "Luis Manuel Sousa\n");
        StringBuilder sb = new StringBuilder();
        Function<String, Integer> f= line -> line.split("\s+").length ;
        Predicate<Integer> p = numberOfNames -> true;
        BiConsumer<Integer, Integer> c= (numberOfNames, order) -> sb.append(sb.isEmpty()?"":"; ").append(order).append('-').append(numberOfNames);
        Names.forEachIf(new BufferedReader(sr), f, p, c );
        assertEquals("1-4; 2-3; 3-2; 4-3", sb.toString());
    }

}
