package trab2.grupo2;

import trab2.grupo1.Name;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;

public class Families {

    public static <V> boolean isOrdered(Iterable<V> serie, Comparator<V> compareValue ) {

        boolean ascending = true;
        boolean descending = true;

        V prev = null;
        for(V curr: serie) {
            if( prev != null && ascending && compareValue.compare(prev, curr) >= 0 ) { //entra se prev for maior do que curr
                ascending = false;                                                     //ja não pode ser por ordem descendente
            }
            else if( prev != null && descending && compareValue.compare(prev, curr) <= 0 ) { //entra se curr for maior do que prev
                descending = false;                                                     //ja não pode ser por ordem crescente
            }
            if( !ascending && !descending) {
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

            S elem;
            if( (elem = map.get(key)) == null ) {
                map.put(key, elem = ss.get());
            }
            elem.add(name);
        }
        return map;
    }
    
    public static  <S extends Set<Name>> void printFamilies( PrintWriter pw, Map<String, S> families ) throws IOException {

        families.forEach( (family, members ) -> {
            pw.println( family + " " + members.size() );
            members.forEach( (name) -> { pw.println("\t" + name.toString()); } );
        } );
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

    public static void merge( File dir, String filenameOut ) throws IOException {

        try(PrintWriter pw = new PrintWriter( filenameOut )) {

            List<String> names = new ArrayList<>();

            File[] listOfFiles = dir.listFiles();
            for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {
                try (BufferedReader rd = new BufferedReader(new FileReader(listOfFiles[i]))) {
                    String line;
                    while( (line = rd.readLine()) != null ) {
                        Name name = new Name(line);
                        names.add(name.getFullName());
                    }
                }
            }
            Collections.sort(names);
            for(String name : names){
                pw.println(name);
            }
        }
    }

    public static void main(String[] args) throws IOException {

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
