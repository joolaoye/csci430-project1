import java.util.*;

public class Warehouse {
    private ClientList clients;
    private ProductInventory products;

    private static Warehouse obj;

    private Warehouse() {}

    public static Warehouse getInstance() {
        if (obj == null)
            obj = new Warehouse();
        return obj;
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
        return clients.getClient(clientID);
    }

    public ArrayList<Client> getClients() {
        return this.clients.getClients();
    }

    public Iterator getWishlist(String clientID) {
        Client client = clients.getClient(clientID);
        return client.getWishlists();
    }
}
