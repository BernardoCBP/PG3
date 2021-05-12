package testes.test1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Team extends Item implements Composite {
    private final Consultant[] members;
    private final Manager leader;

    public Team(char id, Manager leader, Consultant ... members) throws ItemException{
        super( "Team "+ id );
        this.members= Arrays.copyOf(members, members.length);
        Comparator<Item> cmp = (i1, i2) -> i1.toString().compareTo( i2.toString() );
        if (! containsItem( leader, cmp ) )
            throw new ItemException( " - not a team member.", leader );

        Arrays.sort(this.members, cmp);

        this.leader = leader;
    }

    @Override
    public int getCost() {
        int sum = 0;
        for(Consultant c: members )
            sum+= c.getCost();
        return sum;
    }

    public Manager getLeader() { return leader; }

    @Override
    public boolean containsItem(Item it, Comparator<Item> cmp ) {
        for ( Consultant c: members )
            if (cmp.compare(it, c) == 0) return true;
        return false;
    }

    @Override
    public List<Item> getItems( ) {
        return Arrays.asList( members );
    }

    @Override
    public String getDescription() {
        StringBuilder s = new StringBuilder( toString() );
        s.append("\nLeader: ").append(leader.getDescription());;
        s.append("\nMembers:");
        for (Consultant c: members ) {
            if ( c != leader )
                s.append("\n\t").append( c.getDescription() );
        }
        s.append("\nTotal: ").append(getCost()).append("�");
        return s.toString();
    }

    public static void main(String[] args) {
        try {
            Consultant.COST_PER_HOUR=50;
            Consultant[] a = { new Consultant("Rui Dias", 24),
                    new Consultant("Ivo Mota", 8),
                    new Manager("Lia Lima", 24, 200),
                    new Consultant("Jos� Neto", 16) };
            Team t = new Team('A', (Manager)a[2], a);
            System.out.println(t+": "+t.getCost()+"� ("+t.getItems().size()+" membros)");
            System.out.println("Membros: "+t.getItems() +" L�der: " + t.getLeader() );

            System.out.println( t.getDescription() );

            Team te = new Team('E', new Manager("Jos� Silva",5,200), a); // Lan�a exce��o
        } catch (ItemException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
