import java.util.*;
import java.io.*;

public class UserInterface {
    private static UserInterface userInterface;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Warehouse warehouse;

    // Menu options
    private static final int EXIT = 0;
    private static final int ADD_CLIENT = 1;
    private static final int ADD_PRODUCT = 2;
    private static final int SEARCH_CLIENT = 3;
    private static final int SEARCH_PRODUCT = 4;
    private static final int GET_CLIENTS = 5;
    private static final int GET_PRODUCTS = 6;
    private static final int GET_WISHLIST = 7;
    private static final int ADD_TO_WISHLIST = 8;

    //Carson: I added some menu options for the balance.
    //These can be changed around as you see fit.
    private static final int GET_CLIENT_BALANCE = 9;
    private static final int ADD_CLIENT_BALANCE = 10;
    private static final int GET_PRODUCT_QUANTITY = 11;

    
    private UserInterface() {
        warehouse = Warehouse.instance();
    }

    public static UserInterface instance() {
        if (userInterface == null)
            userInterface = new UserInterface();
        return userInterface;
    }

    public static void main(String[] args) {
        UserInterface.instance().run();
    }

    public void run() {
        int command;
        System.out.println("Welcome to the Warehouse Management System!");

        do {
            showMenu();
            command = getCommand();

            switch (command) {
                case ADD_CLIENT:
                    addClient();
                    break;
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case SEARCH_CLIENT:
                    searchClient();
                    break;
                case SEARCH_PRODUCT:
                    searchProduct();
                    break;
                case GET_CLIENTS:
                    getClients();
                    break;
                case GET_PRODUCTS:
                    getProducts();
                    break;
                case GET_WISHLIST:
                    getWishlist();
                    break;
                case ADD_TO_WISHLIST:
                    addToWishlist();
                    break;

                //Carson: Added my own methods to the switch block.
                case GET_CLIENT_BALANCE:
                    getClientBalance();
                    break;
                case ADD_CLIENT_BALANCE:
                    addClientBalance();
                    break;
                case GET_PRODUCT_QUANTITY:
                    getProductQuantity();
                    break;

                    
                case EXIT:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        } while (command != EXIT);
    }

    private void showMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println(ADD_CLIENT + " : Add Client");
        System.out.println(ADD_PRODUCT + " : Add Product");
        System.out.println(SEARCH_CLIENT + " : Search Client by ID");
        System.out.println(SEARCH_PRODUCT + " : Search Product by ID");
        System.out.println(GET_CLIENTS + " : Get All Clients");
        System.out.println(GET_PRODUCTS + " : Get All Products");
        System.out.println(GET_WISHLIST + " : Get Client Wishlist");
        System.out.println(ADD_TO_WISHLIST + " : Add Product to Client Wishlist");
        
        //Added my menu options at the bottom here.
        System.out.println(GET_CLIENT_BALANCE + " : Get Client Balance");
        System.out.println(ADD_CLIENT_BALANCE + " : Add to a Client Balance");
        System.out.println(GET_PRODUCT_QUANTITY + " : Get Product Quantity");
        
        System.out.println(EXIT + " : Exit");
    }

    private int getCommand() {
        int command = -1;
        try {
            System.out.print("Enter your choice: ");
            command = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        return command;
    }

    private void addClient() {
        try {
            System.out.print("Enter client name: ");
            String name = reader.readLine().trim();
            System.out.print("Enter client address: ");
            String address = reader.readLine().trim();

            Client client = warehouse.addClient(name, address);
            if (client != null) {
                System.out.println("Client added successfully! ID: " + client.getId());
            } else {
                System.out.println("Failed to add client.");
            }
        } catch (IOException e) {
            System.out.println("Error reading input.");
        }
    }

    private void addProduct() {
        try {
            System.out.print("Enter product name: ");
            String name = reader.readLine().trim();
            System.out.print("Enter product price: ");
            float price = Float.parseFloat(reader.readLine());
            System.out.print("Enter product quantity: ");
            int quantity = Integer.parseInt(reader.readLine());

            Product product = warehouse.addProduct(name, price, quantity);
            if (product != null) {
                System.out.println("Product added successfully! ID: " + product.getId());
            } else {
                System.out.println("Failed to add product.");
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private void searchClient() {
        try {
            System.out.print("Enter client ID: ");
            String id = reader.readLine().trim();
            Client client = warehouse.searchClient(id);
            if (client != null) {
                System.out.println("Client found: " + client);
            } else {
                System.out.println("Client not found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading input.");
        }
    }

    private void searchProduct() {
        try {
            System.out.print("Enter product ID: ");
            String id = reader.readLine().trim();
            Product product = warehouse.searchProduct(id);
            if (product != null) {
                System.out.println("Product found: " + product);
            } else {
                System.out.println("Product not found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading input.");
        }
    }

    private void getClients() {
        Iterator<Client> iterator = warehouse.getClients();
        if (!iterator.hasNext()) {
            System.out.println("No clients found.");
            return;
        }
        System.out.println("--- Clients List ---");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private void getProducts() {
        Iterator<Product> iterator = warehouse.getProducts();
        if (!iterator.hasNext()) {
            System.out.println("No products found.");
            return;
        }
        System.out.println("--- Products List ---");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private void getWishlist() {
        try {
            System.out.print("Enter client ID: ");
            String clientId = reader.readLine().trim();
            Wishlist wishlist = warehouse.getWishlist(clientId);

            if (wishlist == null || !(wishlist.getProducts().hasNext())) {
                System.out.println("Wishlist is empty or client not found.");
                return;
            }

            System.out.println("--- Wishlist ---");

            Iterator<Item> it  = wishlist.getProducts();

            while (it.hasNext()) {
                Item item = it.next();
                System.out.println(item);
            }

        } catch (IOException e) {
            System.out.println("Error reading input.");
        }
    }

    private void addToWishlist() {
        try {
            System.out.print("Enter client ID: ");
            String clientId = reader.readLine().trim();
            System.out.print("Enter product ID: ");
            String productId = reader.readLine().trim();
            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(reader.readLine());

            Map<String, Object> response = warehouse.addToWishlist(productId, quantity, clientId);
            if (response.get("status").equals("success")) {
                System.out.println("Added to wishlist. Current quantity: " + response.get("current_quantity"));
            } else {
                System.out.println("Failed to add to wishlist: " + response.get("message"));
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }



 //Carson: Added my own voids for my methods down here.
    //I appreciet the framework!
    private void getClientBalance() {
        try {
            System.out.print("Enter client ID: ");
            String id = reader.readLine().trim();
            Client client = warehouse.searchClient(id);

            if (client != null) {
                System.out.printf("Client %s (%s) has a balance of: $%.2f%n", client.getName(), clien>
            } else {
                System.out.println("Client not found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading input.");
        }
    }

    private void addClientBalance() {
        try {
            System.out.print("Enter client ID: ");
            String id = reader.readLine().trim();
            Client client = warehouse.searchClient(id);

            if (client == null) {
                System.out.println("Client not found.");
                return;
            }

            System.out.print("Enter amount to add: ");
            double amount = Double.parseDouble(reader.readLine());

            client.updateBalance(amount);
            System.out.printf("Balance updated. You will be billed by main. New balance for %s: $%.2f>
        } catch (IOException | NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }
    private void getProductQuantity() {
        try {
            System.out.print("Enter product ID: ");
            String id = reader.readLine().trim();
            Product product = warehouse.searchProduct(id);

            if (product != null) {
                System.out.printf("Product %s (%s) has %d units in stock.%n", product.getName(), prod>
            } else {
                System.out.println("Product not found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading input.");
        }
}
}
