package trab2.grupo1;

import java.io.*;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Names {

    public static <V> int forEachIf(BufferedReader in, Function<String, V> makeValue,
                                    Predicate<V> pred, BiConsumer<V, Integer> action) throws IOException {

        int count = 0;
        String line;

        while( (line = in.readLine()) != null ) {
            V value = makeValue.apply(line);
            if( pred.test(value) ) {
                action.accept(value, ++count);
            }
        }
        return count;
    }

    public static void forEachIf(String filename, BiConsumer<Name, Integer> action, Set<String> surnames) throws IOException {
        try( BufferedReader rd = new BufferedReader( new FileReader(filename)) ) {

            forEachIf(rd,
                    Name::new,
                    (value) -> { return surnames.contains(value.getSurname()); },
                    action);
        }
    }

    public static void appendIf(String filenameIn, String filenameOut, Set<String> surnames) throws IOException {
        try(PrintWriter pw = new PrintWriter( new FileWriter(filenameOut, true) ) ) {

                forEachIf(filenameIn,
                        (name, count) -> { pw.println(count.toString() + name.getFullName() ); },
                        surnames);
        }
    }
}