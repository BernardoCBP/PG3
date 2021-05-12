package RepT1;

public class ClockException extends Exception {
    private final Clock c;
    public ClockException(String msg, Clock c) {
        super(msg + c.toString()); //O toString nao era necessario pela concatenação
        this.c = c;
    }
    public ClockException(Clock c) {
        super("Already exists -> " + c);
        this.c = c;
    }
    public Clock GetClock() {
        return this.c;
    }
}