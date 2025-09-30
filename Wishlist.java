import java.util.ArrayList;
import java.util.Iterator;

public class Wishlist {
    private ArrayList<Item> items;

    public Wishlist() {
        items = new ArrayList<>();
    }

    
    public boolean findItem(String productId) {
        for (Item item : items) {
            if (item.getProductID().equals(productId)) {
                return true;
            }
        }
        return false;
    }

    
    public boolean insertItem(String productId, int quantity) {
        for (Item item : items) {
            if (item.getProductID().equals(productId)) {
                item.updateQuantity(quantity);
                return true;
            }
        }
        items.add(new Item(productId, quantity));
        return true;
    }

   
    public Iterator<Item> getItems() {
        return items.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Wishlist: ");
        for (Item item : items) {
            sb.append("[Product: ").append(item.getProductID())
              .append(", Quantity: ").append(item.getQuantity()).append("] ");
        }
        return sb.toString();
    }
}
