package trab1.grupo3;

import java.util.Comparator;

public class SongComparator implements Comparator<Song> {

    @Override
    public int compare(Song o1, Song o2) {
        if(o1.year == o2.year)
            return o1.toString().compareTo(o2.toString());
        return o1.year - o2.year;
    }
}
