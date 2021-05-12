package testes.test1;

public class Consultant extends  Item{
    public static int COST_PER_HOUR;
    private final int workingHours;
    public Consultant( String name, int workingHours) {
        super( name );
        this.workingHours = workingHours;
    }
    @Override
    public int getCost() {
        return COST_PER_HOUR * workingHours;
    }
    public int getWorkingHours() {
        return workingHours;
    }

    public static void main(String[] args) {
        Consultant.COST_PER_HOUR=50;
        Consultant c = new Consultant("Rui Dias", 20);
        System.out.println( "Nome: " + c );
        System.out.println(c.getDescription() + " ("+ c.getWorkingHours()+ " horas)");
    }
}
