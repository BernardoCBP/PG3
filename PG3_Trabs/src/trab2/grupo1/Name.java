package trab2.grupo1;

import java.util.Arrays;

public class Name implements Comparable<Name> {

    private final String fullName;
    private final String firstNames;
    private final String surname;

    public Name(String fullName) {
        this.fullName = fullName.trim().replaceAll("\\s+", " ");
        if( this.fullName.contains(" ") ){
            int index = this.fullName.lastIndexOf(' ');
            firstNames = this.fullName.substring(0, index);
            surname = this.fullName.substring(index+1);
        }
        else{
            firstNames = "";
            surname = this.fullName;
        }
    }

    public final String getFirstNames() {
        return this.firstNames;
    }

    public final String getSurname() {
        return this.surname;
    }

    public final String getFullName() {
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