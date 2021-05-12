package RepT1;

import java.util.Date;

public class HourClock extends AbstractClock{

    public final Date day;

    public HourClock(String d, int h, Date dt) {
        super(d, h);
        day = dt;
    }

    public HourClock(String d, int h) {
        //this( d, h, new Date() );
        super(d, h);
        day = new Date();
    }

    public boolean wakeUp(int h, Date d, Awake aw) {
        if(getHour() == h, d.equals(this.day)) {
            aw.stop( this );
            return true;
        }
        return false;
    }

    public String toString() {
        return super.toString() + String.format("%02d-%02d-%04d (%s)",
                                day.getDay(), day.getMonth(), day.getYear(), day.getWeekDay() );
    }
}
