package RepT1;

public class DailyClock extends AbstractClock {

    private final String[] wd;

    public DailyClock(String name, int h, String ... wd) {
        super(name + Arrays.toString(wd), h);
        this.wd = wd;
    }

    boolean wakeUp(int h, Date d, Awake aw) {
        return ( contains( d.getWeekDay() ) && getHour() == h );
        return false;
    }

    private static boolean contains(String d) {
        //return Arrays.asList(wd).contains(d);
        for(String s: wd) {
            if( s.equals(d) ) return true;
        }
        return false;
    }
}