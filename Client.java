// Name: Carson Stallcup
// Group 3
// StarID: po1278cm
// File: Client.java
// Purpose: This class contains all information pertaining to each user. 

import java.util.ArrayList;
import java.util.UUID; 

public class Client {
    private String id;
    private String name;
    private String address;
    private double balance;
    private ArrayList<Item> wishlist;
    private ArrayList<Item> transactions;
    private ArrayList<Item> waitlist;

    public Client(String name, String address) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.balance = 0.0;
        this.wishlist = new ArrayList<Item>();
        this.transactions = new ArrayList<Item>();
        this.waitlist = new ArrayList<Item>();
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void updateBalance(double amount) {
        this.balance += amount;
    }
    
    public ArrayList<Item> getWishlist() {
        return this.wishlist;
    }

    public ArrayList<Item> getWaitlist() {
        return this.waitlist;
    }

    public ArrayList<Item> getTransactions() {
        return this.transactions;
    }

    @Override
    public String toString() {
        return String.format("Client[%s] %s, %s, Balance: $%.2f",
                id, name, address, balance);
    }
}
