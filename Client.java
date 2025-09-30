// Name: Carson Stallcup
// Group 3
// StarID: po1278cm
// File: Client.java
// Purpose: This class contains all information pertaining to each user. 



import java.util.List;
import java.util.ArrayList; 


public class Client {
    private String id;
    private String name;
    private String address;
    private double balance;


    //Initializing.
    public Client(String id, String name, String address, double balance) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.balance = balance;
    this.wishlists = new ArrayList<>();
    this.transactions = new ArrayList<>();
}


    public Client(String id, String name, String address) {
        this(id, name, address, 0.0);
    }


    //GET Functions
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getBalance() {

        return balance;
    }




    //SET Functions
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void updateBalance(double amount) {
        this.balance += amount;
    }


    private List<Wishlist> wishlists;
    private List<Transaction> transactions;
    private List<WaithtList> waitlist;
    

    //Relationship to wish lists (placeholder)
    public List<Wishlist> getWishlists() {
        return new ArrayList<>(wishlists);
    }




    //Relationship with waitlist (placeholder)
    public List<WaitList> getWaitlist() {
        return new ArrayList<>(waitlist);
    }


    
    //Relationship to transactions (placeholder)
    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    //Information display
    @Override
    public String toString() {
        return String.format("Client[%s] %s, %s, Balance: $%.2f",
                id, name, address, balance);
    }
}
