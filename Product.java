// Name: Sohaib Akram
// Group 3
// StarID: ho7246ag
// File: product.java
// Purpose: This class contains all information pertaining to each product
// that is within the product inventory.  




public class Product {
    private String id;
    private String name;
    private int amount;
    private float salePrice;


    //Initializing.
    public Product(String id, String name, int amount, float salePrice) {
    this.id = id;
    this.name = name;
    this.amount = amount;
    this.salePrice = salePrice;
}



    //GET Functions
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public float getSalePrice() {

        return salePrice;
    }




    //SET Functions
    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setsalePrice(float salePrice) {
        this.salePrice = salePrice;
    }


    //Information display
   @Override
public String toString() {
    return String.format("Product[%s] %s, Quantity: %d, Price: $%.2f",
            id, name, amount, salePrice);
}
