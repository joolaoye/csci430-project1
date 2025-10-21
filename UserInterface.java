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
    private static final int RECEIVE_SHIPMENT = 9; // New option

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
                case RECEIVE_SHIPMENT:
                    receiveShipment(); // New case
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
        System.out.println(RECEIVE_SHIPMENT + " : Receive Shipment"); // New option
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

    private void receiveShipment() {
        try {
            System.out.print("Enter product ID: ");
            String productId = reader.readLine().trim();
            System.out.print("Enter shipment quantity: ");
            int quantity = Integer.parseInt(reader.readLine());

            String result = warehouse.receiveShipment(productId, quantity);
            System.out.println(result);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }
}
