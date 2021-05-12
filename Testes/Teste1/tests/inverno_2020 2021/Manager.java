package testes.test1;

public class Manager extends Consultant {
    public final int premium;
    public Manager(String name, int workingHours, int premium) {
        super( name+" (PM)", workingHours );
        this.premium = premium;
    }
    @Override
    public int getCost() {
        return super.getCost() + premium;
    }

    public static void main(String[] args) {
        Consultant.COST_PER_HOUR=50;
        Manager m = new Manager("Eva Silva",10,200);
        System.out.println( "Nome: " + m );
        System.out.println(m.getDescription() +" (prémio "+ m.premium + "€)");
    }

}
