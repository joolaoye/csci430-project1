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

    public boolean addClient(String name, String address, float balance) {
       return true;
    }

    public boolean addProduct(String name, float SalePrice, int amount) {
        return true;
    }

    
}
