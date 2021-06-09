package trab2.grupo1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Names {

    public static <V> int forEachIf(BufferedReader in, Function<String, V> makeValue, Predicate<V> pred,
                                    BiConsumer<V, Integer> action) throws IOException {
        String line;
        int count = 0;
        while( (line = in.readLine()) != null ) {
            V value = makeValue.apply( line );
            if( pred.test( value ) ) {
                action.accept(value, ++count);
            }
        }
        return count;
    }

    public static void forEachIf(String filename, BiConsumer<Name, Integer> action, Set<String> surnames) throws IOException {
        try( BufferedReader inputStream = new BufferedReader(new FileReader(filename)) ) {
            forEachIf(inputStream,
                    (line) -> { return new Name(line); },                                   // Function
                    (value) -> { return surnames.contains( value.getSurname() ); },         // Predicate
                    action);
        }
    }

    public static void appendIf(String filenameIn, String filenameOut, Set<String> surnames) throws IOException {
        try(PrintWriter outputStream = new PrintWriter( filenameOut ) ) {
            forEachIf(filenameIn,
                    (name, integer) -> {
                        outputStream.append(String.valueOf(integer)).append(name.getFullName());
                    },
                    surnames);
        }
    }

}
