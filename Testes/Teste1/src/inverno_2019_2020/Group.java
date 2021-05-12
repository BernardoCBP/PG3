package inverno_2019_2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Group implements Register {

    private List<Register> registers = new ArrayList<>();


    public Date getNextVaccinate() {
        Date less = null;
        for(Register r: registers) {
            Date d = r.getNextVaccinate();
            if( d != null)
                if( less == null || less.compareTo(d) > 0)
                    less = d;
        }
        return less;
    }

    public Group append(Register reg) {
        registers.add(reg);
        return this;
    }

    public List<Register> getRegisters(Predicate<Register> filter) {
        List<Register> newRegisters = new ArrayList<>();
        for(Register r: registers) {
            if( filter.test(r) )
                newRegisters.add(r);
        }
        return newRegisters;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Register r: registers){
            s.append(r).append("\n");
        }
        return s.toString();
    }

    public static int getNullNextVaccinate(Group g) {
        return g.getRegisters((r) -> r.getNextVaccinate() == null).size();
    }

    public static List<Register> getRegisters(Group g) {
        return g.getRegisters((r) -> true);
    }
}
