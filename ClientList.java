// Name: Carson Stallcup
// StarID: po1278cm
// Group 3
// Purpose: This class contains many clients.  It can get, 
// add, or delete clients and modify the list.

import java.util.*;

public class ClientList {
    private Map<String, Client> clients;

    public ClientList() {
        this.clients = new HashMap<>();
    }


    //Adding a client to the list.
    public Client addClient(String name, String address) {
        String uniqueId = UUID.randomUUID().toString();
        Client client = new Client(uniqueId, name, address, 0.0);
        clients.put(uniqueId, client);
        return client;
    }







    //Used to delete a particular client.
    public void deleteClient(String clientId) {
        if (!clients.containsKey(clientId)) {
            throw new NoSuchElementException("No client with ID " + clientId + " found.");
        }
        clients.remove(clientId);
    }










    //Used to get a particular client.
    public Client getClient(String clientId) {
        return clients.get(clientId);
    }
    

    //Used to display all clients.
    public List<Client> listClients() {
        return new ArrayList<>(clients.values());
    }






    //Searching fir a client by their name.
    public List<Client> searchByName(String name) {
        List<Client> result = new ArrayList<>();
        for (Client c : clients.values()) {
            if (c.getName().equalsIgnoreCase(name)) {
                result.add(c);
            }
        }
        return result;
    }











    //Gets transactions through the client and shows all transactions a client has ever conducted.
    public List<Transaction> getAllTransactions() {
        List<Transaction> allTransactions = new ArrayList<>();
        for (Client c : clients.values()) {
            allTransactions.addAll(c.getTransactions());
        }
        return allTransactions;
    }








    //Displays all items on a client's wish list based on the relationship between the client and their wish list.
    public List<Wishlist> getAllWishlists() {
        List<Wishlist> allWishlists = new ArrayList<>();
        for (Client c : clients.values()) {
            allWishlists.addAll(c.getWishlists());
        }
        return allWishlists;
    }



    //Displays all items in a waitlist, similar to the wishlists.
    public List<WaitList> getWaitList() {
        List<WaitList> allWaitList = new ArrayList<>();
        for (Client c : clients.values()) {
            allWaitList.addAll(c.getWaitlist());
        }
        return allWaitList;
    }
}

