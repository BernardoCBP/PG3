package testes.test1;

public class ItemException extends Exception {
    private final Item item;
    public ItemException( String msg , Item item ) {
        super( item + msg );
        this.item = item;
    }
    public Item getItem() {
        return this.item;
    }
}
