package trab2.grupo2;

import trab2.grupo1.Name;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class Families {

    public static <V> boolean isOrdered(Iterable<V> serie, Comparator<V> compareValue ) {
        V prev = null;
        for(V curr: serie) {
            if( prev != null && compareValue.compare(prev, curr) > 0 ) { //entra se prev for maior do que curr
                break;
            }
            prev = curr;
        }

        prev = null;
        for(V curr: serie) {
            if( prev != null && compareValue.compare(prev, curr) < 0 ) { //entra se curr for maior do que prev
                return false;
            }
            prev = curr;
        }
        return true;
    }

    public static <S extends Set<Name>> Map<String, S> families(BufferedReader in, Supplier<Map<String, S>> sm,
                                                               Supplier<S> ss ) throws IOException {

        Map<String, S> map = sm.get();

        String line;
        while( (line = in.readLine()) != null ) {

            Name name = new Name(line);
            String key = name.getSurname();

            if( !map.containsKey(key) ) {
                map.put(key, ss.get());
            }
            map.get(key).add(name);
        }
        return map;
    }
    
    public static  <S extends Set<Name>> void printFamilies( PrintWriter pw, Map<String, S> families ) throws IOException {

        families.forEach( (family, members ) -> {
            pw.println( family + " " + members.size() );
            for(Name names: members) {
                pw.println("\t" + names);
            }
        }  );
    }

    public static <S extends Set<Name>> Set<String> greaterFamilies( Map<String, S> families ) throws IOException {

        if ( families.isEmpty() ) return Collections.emptySet();

        SortedSet<String> mostMembers = new TreeSet<>(Collections.reverseOrder());
        int max = 0;
        for (Map.Entry<String, S> entry : families.entrySet()) {
            if( entry.getValue().size() == max) {
                mostMembers.add(entry.getKey());
            }
            else if ( entry.getValue().size() > max) {
                max = entry.getValue().size();
                mostMembers.clear();
                mostMembers.add(entry.getKey());
            }
        }
        return mostMembers;
    }

    void merge( File dir, String filenameOut ) throws IOException {

    }

    public static void main(String[] args) throws IOException {

        String[] OutFiles = { "families.txt" };

        try ( BufferedReader inputStream = new BufferedReader( new FileReader("Names.txt" ) ) ) {

            try(PrintWriter pwf = new PrintWriter( "families.txt"  ) )  {
                Families.printFamilies(pwf, Families.families(new BufferedReader( new FileReader("Names.txt" ) ), HashMap::new, TreeSet::new));
            }

            try(PrintWriter pws = new PrintWriter( "sortFamilies.txt" ) )  {
                Families.printFamilies(pws, Families.families(new BufferedReader( new FileReader("Names.txt" ) ), TreeMap::new, TreeSet::new));
            }

            try(PrintWriter pwo = new PrintWriter( "OriginalFamilies.txt" ) )  {
                Families.printFamilies(pwo, Families.families(new BufferedReader( new FileReader("Names.txt" ) ), LinkedHashMap::new, TreeSet::new));
            }
        }
    }

}
