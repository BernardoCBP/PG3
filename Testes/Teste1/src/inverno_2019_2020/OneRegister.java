package inverno_2019_2020;

public class OneRegister implements Register{

    private Date nextVaccinate;
    private final String specie;
    public final int registerId;

    public OneRegister(String specie, int regId) {
        this.specie = specie;
        this.registerId = regId;
    }

    public boolean contains(int regId) {
        return this.registerId == regId;
    }

    public Date getNextVaccinate() {
        return this.nextVaccinate;
    }

    public void advanceVAccinateDate(int days) {
        if(this.nextVaccinate == null) {
            this.nextVaccinate = new Date();
        }
        this.nextVaccinate = nextVaccinate.getDate(days);

    }

    public boolean isVaccinateInDay() throws RegisterException {
        if(this.nextVaccinate == null )
            throw new RegisterException("Erro nas datas dos registos");
        Date currentDate = new Date();
        return this.nextVaccinate.compareTo(currentDate) > 0;
    }

    public String toString() {
        return this.specie + "registado com o número" + this.registerId;
    }


    public static void main(String[] args) {
        OneRegister c = new OneRegister("cão", 123);
        System.out.println( c );
    }
}