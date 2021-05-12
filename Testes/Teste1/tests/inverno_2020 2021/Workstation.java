package testes.test1;

public class Workstation extends Item{
    private final int cost;
    public Workstation( int number, int cost) {
        super("Workstation "+ number);
        this.cost = cost;
    }
    @Override
    public int getCost() { return cost; }

    public static void main(String[] args) {
        Workstation w = new Workstation(3, 600);
        System.out.println( "Nome: " + w.toString() );
        System.out.println( w.getDescription() );
    }
}
