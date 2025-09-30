import java.util.*;

public class Warehouse {
    private ClientList clients;
    private ProductInventory products;
    private static Warehouse warehouse;

    private Warehouse() {
        this.clients = ClientList.instance();
    }

    public static Warehouse instance() {
        if (warehouse == null)
            warehouse = new Warehouse();
        return warehouse;
    }

    public Client addClient(String name, String address) {
        if (name == null || name.isBlank() || address == null || address.isBlank()) {
            return null;
        }

        Client newcClient = new Client(name, address);
        if (clients.insertClient(newcClient)) {
            return newcClient;
        }
        
        return null;
    }

    public Product addProduct(String name, float salePrice, int amount) {
        return products.addProduct(name, salePrice, amount);
    }

    public Client searchClient(String clientID) {
        return clients.searchClient(clientID);
    }

    public ArrayList<Client> getClients() {
        return this.clients.getClients();
    }

    public Iterator<Item> getWishlist(String clientID) {
        Client client = clients.searchClient(clientID);
        Wishlist wishlist =  client.getWishlist();
        return wishlist.getItems();
    }
}
