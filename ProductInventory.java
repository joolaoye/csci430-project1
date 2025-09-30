// Name: Sohaib Akram
// Group 3
// Purpose: This class contains many products.  It can get, 
// add, or delete products and modify the list.

import java.util.*;

public class ProductInventory {
    private Map<String, Product> products;

    public ProductInventory() {
        this.products = new HashMap<>();
    }

    // 1. Inserting a new product
    public Product insertProduct(String name, int amount, float salePrice) {
        String uniqueId = UUID.randomUUID().toString();
        Product product = new Product(uniqueId, name, amount, salePrice);
        products.put(uniqueId, product);
        return product;
    }


    // 2. Get a specific product
    public Product getProduct(String productId) {
        return products.get(productId);
    }

    // 3. Get all products as an iterator
    public Iterator<Product> getProducts() {
        return products.values().iterator();
    }

    // 4. Search for a product by ID (returns boolean)
    public boolean searchProduct(String productId) {
        return products.containsKey(productId);
    }
}
