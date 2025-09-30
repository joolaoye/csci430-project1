import java.util.ArrayList;
import java.util.Iterator;

public class Wishlist {
    private ArrayList<Item> items;

    public Wishlist() {
        items = new ArrayList<Item>();
    }

    public Item findItem(String productId) {
        for (Item item : items) {
            if (item.getProductId().equals(productId)) {
                return item;
            }
        }
        return null;
    }

    public boolean insertItem(Item item) {
        items.add(item);
        return true;
    }

    public Iterator<Item> getProducts() {
        return items.iterator();
    }
}
