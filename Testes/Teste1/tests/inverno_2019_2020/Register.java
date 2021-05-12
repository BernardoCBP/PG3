package tests.test1;


import trab1.grupo1.Date;

/**
 * Created by msousa on 11/7/2019.
 */
public interface Register {
    boolean contains( int regId);
    Date getNextVaccinate();
    void advanceVaccinateDate(int days );
    boolean isVaccinateInDay() throws RegisterException;
}
