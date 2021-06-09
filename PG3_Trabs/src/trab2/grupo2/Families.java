package trab2.grupo2;

import trab2.grupo1.Name;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class Families {

    public static <V> boolean isOrdered(Iterable<V> serie, Comparator<V> compareValue ) {
        V prev = null;
        for( V curr: serie ) {
            if( prev != null && compareValue.compare(prev, curr) > 0 ) {
                if( compareValue.compare(prev, curr) < 0 )
                        return false;
            }
            prev = curr;
        }
        return true;
    }

    public static <S extends Set<Name>> Map<String, S> families(BufferedReader in, Supplier<Map<String, S>> sm,
                                                               Supplier<S> ss ) throws IOException {

        String line;
        Map<String, S> map = sm.get();

        while( (line = in.readLine()) != null ) {
            Name name = new Name(line);
            String key = name.getSurname();    // TODO: 27/05/2021 -> .toLowerCase() para nao haver distincao

            if( !map.containsKey(key) ) {
                map.put(key, ss.get());
            }
            map.get(key).add(name);
        }
        return map;
    }
    
    public static  <S extends Set<Name>> void printFamilies( PrintWriter pw, Map<String, S> families ) throws IOException {
        // TODO: 27/05/2021
    }

    public static <S extends Set<Name>> Set<String> greaterFamilies( Map<String, S> families ) throws IOException {
        // TODO: 27/05/2021
        return null;
    }

    public static void main(String[] args) {
        // TODO: 27/05/2021
    }

}
