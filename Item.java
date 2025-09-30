public class Item {
    private String productId;
    private int quantity;

    public Item(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return this.productId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setProductId(String value) {
        this.productId = value;
    }

    public void setQuantity(int value) {
        this.quantity = value;
    }

    public void updateQuantity(int value) {
        this.quantity += value;
    }
}
