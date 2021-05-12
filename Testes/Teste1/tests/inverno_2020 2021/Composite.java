package testes.test1;

import java.util.Comparator;
import java.util.List;

public interface Composite {
    boolean containsItem(Item key, Comparator<Item> cmp);
    List<Item> getItems();
}
