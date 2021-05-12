package tests.test1;

import trab1.grupo1.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


/**
 * Created by msousa on 11/7/2019.
 */
public class Group implements Register {
    private List<Register> registers = new ArrayList<>();


    @Override
    public Date getNextVaccinate() {
        Date less= null;
        for ( Register r: registers ) {
            Date d = r.getNextVaccinate();
            if ( d != null)
                if ( less == null || less.compareTo(d)> 0)
                    less= d;

        }
        return less;
    }


    public Group append(Register r) {
        registers.add( r );
        return this;
    }


    public List<Register> getRegisters(Predicate<Register> filter) {
        List<Register> l= new ArrayList<>();
        for ( Register r: registers )
            if ( filter.test( r )) l.add( r );
        return l;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for ( Register r: registers )
            s.append(r).append('\n');
        if ( s.length() != 0 ) s.delete(s.length()-1, s.length());
        return s.toString();
    }

    /* Não eram para implementar no teste */
    @Override
    public boolean contains(int regId) {
        for ( Register r: registers )
            if (r.contains( regId ))
                return true;
        return false;
    }

    @Override
    public boolean isVaccinateInDay() throws RegisterException {
        for ( Register r: registers ) {
            if ( !r.isVaccinateInDay() )
                return false;
        }
        return true;
    }

    @Override
    public void advanceVaccinateDate(int days) {
        Date dn = getNextVaccinate();
        if (dn != null)
            for (Register r : registers) {
                Date d = r.getNextVaccinate();
                if (dn.equals(d))
                    r.advanceVaccinateDate(days);
            }
    }

    public static int getNullNextVaccinate(Group g) {
        return g.getRegisters((r) -> r.getNextVaccinate()==null).size();
    }

    public static List<Register> getRegisters(Group g) {
        return g.getRegisters((r) -> true );
    }
    public static void main(String[] args) {
        Group g = new Group();
        OneRegister c = new OneRegister("cão", 123);
        SameSpecieGroup sg = new SameSpecieGroup("vara",10, "porco", 140);
        g.append( c ).append( sg );
        c.advanceVaccinateDate(365);
        System.out.println( c.getNextVaccinate());
        System.out.println( "toString:\n" + g );
        System.out.println( "All " +
                g.getRegisters((r) -> true ).toString().replaceAll(", ", "\n"));
        System.out.println( "Null " +
                g.getRegisters((r) -> r.getNextVaccinate()==null ).toString().replaceAll(", ", "\n"));
    }
    public Iterable<Register> registers() {
        return registers;
    }

}
