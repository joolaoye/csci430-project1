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
                response.put("message", "Product not found");
                return response;
            }

            Wishlist wishlist = this.getWishlist(clientId);
            if (wishlist == null) {
                response.put("message", "Client not found");
                return response;
            }

            WishlistItem item = wishlist.findItem(clientId);
            if (item != null) {
                item.updateQuantity(quantity);
            } else {
                item = new WishlistItem(productId, quantity);
                wishlist.insertItem(item);
            }

            response.put("status", "success");
            response.put("current_quantity", item.getQuantity());
        } catch (Exception e) {
            response.put("message", e.getMessage());
        }
        return response;
    }

    // New methods for Receive Shipment
    public String receiveShipment(String productId, int shipmentQuantity) {
        Product product = this.searchProduct(productId);
        if (product == null) {
            return "Product not found.";
        }

        Waitlist waitlist = product.getWaitlist();
        if (waitlist != null) {
            Iterator<WaitlistItem> iterator = waitlist.getItems();
            ArrayList<WaitlistItem> toRemove = new ArrayList<>();

            while (iterator.hasNext() && shipmentQuantity > 0) {
                WaitlistItem item = iterator.next();
                int requestedQty = item.getQuantity();

                if (requestedQty <= shipmentQuantity) {
                    order(productId, requestedQty, item.getClientId());
                    shipmentQuantity -= requestedQty;
                    toRemove.add(item);
                }
            }
            for (WaitlistItem item : toRemove) {
                waitlist.removeItem(item);
            }
        }

        if (shipmentQuantity > 0) {
            product.updateQuantity(shipmentQuantity);
            return "Shipment processed successfully. Remaining " + shipmentQuantity + " units added to inventory.";
        } else {
            return "Shipment processed. All quantity used to fulfill waitlists.";
        }
    }

    public InvoiceItem order(String productId, int quantity, String clientId) {
        Product product = this.searchProduct(productId);
        Client client = this.searchClient(clientId);
        InvoiceItem invoiceItem = null;

        if (quantity > product.getAmount()) {
            invoiceItem = new InvoiceItem(product.getAmount(), product.getName(), product.getSalePrice());
            product.updateQuantity(-product.getAmount());
            float cost = product.getAmount() * product.getSalePrice();
            client.updateBalance(cost);
            int extra = quantity - product.getAmount();
            WaitlistItem waitlistItem = new WaitlistItem(clientId, extra);
            Waitlist product_waitlist = product.getWaitlist();
            product_waitlist.insertItem(waitlistItem);
        } else {
            invoiceItem = new InvoiceItem(quantity, product.getName(), product.getSalePrice());
            product.updateQuantity(-quantity);
            float cost = quantity * product.getSalePrice();
            client.updateBalance(cost);
        }

        return invoiceItem;
    }
    
    public void receivePayment(String clientId, double amount) {
        Client client = clients.searchClient(clientId);
        if (client != null) {
            client.pay(amount);
            System.out.println("Payment of $" + amount + " received from " + client.getName());
            System.out.println("Updated balance: $" + client.getBalance());
        } else {
            System.out.println("Client not found.");
        }
    }
}
