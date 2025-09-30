import java.util.ArrayList;
import java.util.Iterator;

public class Wishlist {
    private ArrayList<Item> items;

    public Wishlist() {
        items = new ArrayList<Item>();
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
