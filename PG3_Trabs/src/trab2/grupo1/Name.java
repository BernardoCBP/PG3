package trab2.grupo1;

import java.util.Arrays;

public class Name implements Comparable<Name> {

    private final String fullName;
    private final String firstNames;
    private final String surname;

    public Name(String fullName) {
        this.fullName = fullName.trim().replaceAll("\\s+", " ");
        String[] name = this.fullName.split(" ");
        this.firstNames = String.join(" ", Arrays.copyOf(name, name.length-1));
        this.surname = name[name.length-1];
    }

    public String getFirstNames() {
        return this.firstNames;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getFullName() {
        return this.fullName;
    }

    @Override
    public String toString() {
        return this.firstNames.isEmpty() ? getSurname() : getSurname() + ", " + getFirstNames();
    }

    @Override
    public boolean equals(Object obj) {
        if( obj == null ) return false;
        if( !(obj instanceof Name) ) return false;
        Name name = (Name) obj;
        return this.toString().compareTo(name.toString()) == 0;
    }

    public int hashCode() {
        return this.fullName.hashCode();
    }

    public int compareTo(Name n) {
        return this.toString().compareTo(n.toString());
    }
}