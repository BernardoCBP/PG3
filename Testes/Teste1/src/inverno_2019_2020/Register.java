package inverno_2019_2020;

public interface Register {
    boolean contains(int regId);
    Date getNextVaccinate();
    oid advanceVaccinateDate(int days);
    boolean isVaccinateInDay() throws RegisterException;
}
