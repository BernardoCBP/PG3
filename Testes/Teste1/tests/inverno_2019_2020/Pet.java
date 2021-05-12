package tests.test1;

/**
 * Created by msousa on 11/7/2019.
 */
public class Pet extends OneRegister {
    private final String name;
    public Pet(String name, String specie, int regId) {
        super(specie, regId);
        this.name = name;
    }
    @Override
    public String toString() {
        return '"' + name + "\" " + super.toString();
    }

    public final String getName() {
        return name;
    }

    public static void main(String[] args) {
        Pet p = new Pet( "Lua", "cão", 124);
        System.out.println( p );


    }
}
