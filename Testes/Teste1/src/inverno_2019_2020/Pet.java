package inverno_2019_2020;

public class Pet extends OneRegister {

    private final String name;

    public Pet(String name, String specie, int regId) {
        super(specie, regId);
        this.name = name;

    }

    public final String getName() {
        return this.name;
    }

    public String toString() {
        return '"' + this.name + '"' + super.toString();
    }
}
