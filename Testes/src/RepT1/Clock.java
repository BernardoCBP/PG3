package RepT1;

public interface Clock {
    int getHour();
    boolean wakeUp(int h, Date d, Awake aw);
    boolean equalDescription(Object o);
}