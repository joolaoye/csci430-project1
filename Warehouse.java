import java.util.*;

public class Warehouse {
    private ClientList clients;
    private ProductInventory products;
    private static Warehouse warehouse;

    private Warehouse() {
        this.clients = ClientList.instance();
        this.products = ProductInventory.instance();
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

        Client newClient = new Client(name, address);
        if (this.clients.insertClient(newClient)) {
            return newClient;
        }
        
        return null;
    }

    public Product addProduct(String name, float salePrice, int amount) {
        if (name == null || name.isBlank() || salePrice <= 0 || amount <= 0) {
            return null;
        }
        
        Product newProduct = new Product(name, amount, salePrice);
        if (this.products.insertProduct(newProduct)) {
            return newProduct;
        }

        return null;
    }

    public Client searchClient(String clientId) {
        return this.clients.searchClient(clientId);
    }

    public Product searchProduct(String productId) {
        return this.products.searchProduct(productId);
    }

    public Iterator<Client> getClients() {
        return this.clients.getClients();
    }

    public Iterator<Product> getProducts() {
        return this.products.getProducts();
    }

    public Wishlist getWishlist(String clientID) {
        Client client = clients.searchClient(clientID);
        return client.getWishlist();
    }

    public Map<String, Object> addToWishlist(String productId, int quantity, String clientId) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "failure");

        try {
            Product product = this.searchProduct(productId);
            if (product == null) {
                response.put("status", "failure");
                response.put("message", "Product not found");
                return response;
            }

            Wishlist wishlist = this.getWishlist(clientId);
            if (wishlist == null) {
                response.put("status", "failure");
                response.put("message", "Client not found");
                return response;
            }

            WishlistItem item = wishlist.findItem(productId);

            if (item != null) {
                item.updateQuantity(quantity);
            }
            else {
                item = new WishlistItem(productId, quantity);
                wishlist.insertItem(item);
            }

            response.put("status", "success");
            response.put("current_quantity", item.getQuantity());
        }
        catch (Exception e) {
            response.put("status", "failure");
            response.put("message", e.getMessage());
        }

        return response;
    }
}
