package inverno_2019_2020;

public class SameSpecieGroup implements Register{

    private final String colName;
    private final int dim;
    private final OneRegister reg;

    public SameSpecieGroup(String collectiveName, int dimension, OneRegister reg) {
        this.colName = collectiveName;
        this.dim = dimension;
        this.reg = reg;
    }

    public SameSpecieGroup(String collectiveName, int dimension, String specie, int firstRegisterId) {
        this(collectiveName, dimension, new OneRegister(specie, firstRegisterId));
    }

    public boolean contains(int regId) {
        int id = this.reg.registerId;
        return regId >= id && regId < id + this.dim);
    }

    public Date getNextVaccinate() {
        return this.reg.getNextVaccinate();
    }

    public void advanceVaccinateDate(int days) {
        this.reg.advanceVAccinateDate(days);
    }

    public boolean isVaccinateInDay(){
        return this.reg.isVaccinateInDay();
    }

    public String toString() {
        return this.colName + "(" + this.dim + ") -" + this.reg + "a" + (reg.registerId + dimension - 1);

    }
}
