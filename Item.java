public class Item {
    private String productID;
    private int quantity;

    public Item(String productID, int quantity) {
        this.productID = productID;
        this.quantity = quantity;
    }

    public String getProductID() {
        return this.productID;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setProductID(String value) {
        this.productID = value;
    }

    public void setQuantity(int value) {
        this.quantity = value;
    }

    public void updateQuantity(int value) {
        this.quantity += value;
    }
}
