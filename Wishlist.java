import java.util.ArrayList;
import java.util.Iterator;

public class Wishlist {
    private ArrayList<WishlistItem> items;

    public Wishlist() {
        items = new ArrayList<WishlistItem>();
    }

    public WishlistItem findItem(String productId) {
        for (WishlistItem item : items) {
            if (item.getProductId().equals(productId)) {
                return item;
            }
        }
        return null;
    }

    public boolean insertItem(WishlistItem item) {
        items.add(item);
        return true;
    }

    public Iterator<WishlistItem> getProducts() {
        return items.iterator();
    }
}
