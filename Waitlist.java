import java.util.ArrayList;
import java.util.Iterator;

public class Waitlist {
    private ArrayList<Item> items;

    public Waitlist() {
        items = new ArrayList<>();
    }

    public boolean findItem(String productId) {
        for (Item item : items) {
            if (item.getProductId().equals(productId)) {
                return true;
            }
        }
        return false;
    }

    public boolean insertItem(String productId, int quantity) {
        items.add(new Item(productId, quantity));
        return true;
    }
   
    public Iterator<Item> getItems() {
        return items.iterator();
    }
}
