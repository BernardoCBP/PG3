package RepT1;

public abstract class AbstractClock implements Clock {

    private final int hour; //final porque não há set
    private final String description;

    protected AbstractClock(String d, int h) {
        this.hour = h;
        this.description = d;
    }

    public final int getHour() { //é final porque não pode ser redefinido
        return this.hour;
    }

    public boolean equalsDescription(Object o) {
        if(o == null) return false;
        if(o instanceof AbstractClock) {
            AbstractClock c = (AbstractClock) o;
            return this.description.equals(c.description);
        }
        return false;
    }

    public String toString() {
        return String.format("%s: %02d:00", this.description , hour)
    }
}