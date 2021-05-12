package tests.test1;


import trab1.grupo1.Date;

/**
 * Created by msousa on 11/7/2019.
 */
public class SameSpecieGroup implements Register{

    private final OneRegister reg;
    private final String collectiveName;
    private final int dimension;

    public SameSpecieGroup(String collectiveName, int dimension,
                           OneRegister r) {
        this.reg = r;
        this.collectiveName = collectiveName;
        this.dimension = dimension;
    }

    public SameSpecieGroup(String collectiveName, int dimension,
                           String specie, int regId) {
        this(collectiveName, dimension, new OneRegister(specie, regId));
    }

    @Override
    public boolean contains(int regId) {
        int id =reg.registerId;
        return regId >= id && regId < id+ dimension;
    }

    @Override
    public Date getNextVaccinate() {
        return reg.getNextVaccinate();
    }

    @Override
    public void advanceVaccinateDate(int days) {
        reg.advanceVaccinateDate( days );
    }

    @Override
    public boolean isVaccinateInDay() throws RegisterException {
        return reg.isVaccinateInDay();
    }

    public String toString() {
        return collectiveName + " (" + dimension + ") - "+
                reg + " a " + (reg.registerId+ dimension -1);
    }

    public static void main(String[] args) {
        OneRegister p = new OneRegister("porco", 140);
        System.out.println( p );
        Register r1 = new SameSpecieGroup( "vara",10, p );
        System.out.println( r1 );

        Register r2 = new SameSpecieGroup("vara",10, "porco", 140);
        System.out.println( r2 );
   }
}
