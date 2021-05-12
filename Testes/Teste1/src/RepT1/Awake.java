package RepT1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public class Awake implements Iterable<Clock> {

    protected final ArrayList<Clock> clocks = new ArrayList<>(); //nao sabemos a dimensao por isso escolhemos memoria dinamica

    public Clock getClock(Predicate<Clock> filter) {
        for(Clock c: clocks) {
            if(filter.test( c ))
                return c;
        }
        return null;
    }

    public Awake append(Clock cp) throws ClockException {
        Clock ce = getClock( c -> c.equalDescription(cp) );
        if( ce != null)
            throw new ClockException( ce );

        clocks.add( cp);
        return this;
    }

    public void stop(Clock c) {
        clocks.remove( c );
    }

    public Iterator<Clock> iterator() {
        return clocks.iterator();
    }


    public String toString() {
        clocks.sort( (c1, c2) -> c1.getHour() - c2.getHour() );
        //clocks.sort(Comparator.comparingInt(Clock::getHour));
        StringBuilder sb = new StringBuilder();
        for(Clock c: clocks) {
            sb.append( c.toString() ).append('\n');
        }
        return sb.toString();
    }


    public Iterator<Clock> iterator() {
        return null;
    }
}
