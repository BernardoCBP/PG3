package testes.test1;

public abstract class Item {
    private final String name;
    protected Item( String name )  { this.name = name;                          }
    public final String toString() { return name;                               }
    public String getDescription() { return name + ": " + getCost()+ "€" ;      }
    public abstract int getCost();
}

