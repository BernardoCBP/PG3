package tests.test1;


import trab1.grupo1.Date;

/**
 * Created by msousa on 11/7/2019.
 */
public class OneRegister implements Register {
    private Date nextVaccinate;
    private final String specie;
    public final int registerId;

    public OneRegister( String specie, int regId ) {
        this.specie = specie;
        registerId= regId;
    }

    @Override
    public boolean contains( int regId) {
        return regId == registerId;
    }

    @Override
    public Date getNextVaccinate() {
        return nextVaccinate;
    }

    @Override
    public void advanceVaccinateDate(int days ) {
        if ( nextVaccinate == null )
            nextVaccinate = new Date();
        nextVaccinate = nextVaccinate.getDate( days );
    }
    @Override
    public boolean isVaccinateInDay() throws RegisterException {
        if ( nextVaccinate == null )
            throw new RegisterException( );
        Date currentDate = new Date();
        return currentDate.compareTo(nextVaccinate) < 0;
    }

    @Override
    public String toString() {
        return specie + " registado com o número " + registerId;
    }

    public static void main(String[] args) {
        OneRegister c = new OneRegister("cão", 123);
        System.out.println( c );
    }
}
