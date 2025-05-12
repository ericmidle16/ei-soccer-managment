package com.ei.eisoccermanagment.soccer.model;


import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    // Map to associate a product to quantity
    private Map<Product, Integer> contents;

    public ShoppingCart() {
        contents = new HashMap<>();
    }

    // Method to add a product to a user's cart
    public void addProduct(Product product, int quantity) {
        if(product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if(quantity < 1) {
            throw new IllegalArgumentException("Quantity cannot be less than 1");
        }
        contents.put(product, contents.getOrDefault(product, 0) + quantity);
    }

    public void updateProduct(Product product, int quantity) {
        if(product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if(quantity < 1) {
            throw new IllegalArgumentException("Quantity cannot be less than 1");
        }
        contents.put(product, quantity);
    }

    public void deleteProduct(Product product) {
        if(product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if(contents.containsKey(product)) {
            contents.remove(product);
        }
    }

    // Method to view the cart contents
    public Map<Product, Integer> getCartContents() {
        return contents;
    }

    public int getTotalProductCount() {
        int total = 0;
        // Loop through the Map's values (quantities)
        for(int quantity: contents.values()) {
            total += quantity;
        }
        return total;
    }

    public double getTotalPrice() {
        double total = 0;
        for(Map.Entry<Product, Integer> entry: contents.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double price = product.getPrice();
            total += quantity * price;
        }
        return total;
    }

    public Map<Product, Integer> getContents() {
        return contents;
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Product product1 = ProductDAO.getCart(7);
        Product product2 = ProductDAO.getCart(8);
        Product product3 = ProductDAO.getCart(7);
        cart.addProduct(product1, 1);
        cart.addProduct(product2, 2);
        cart.addProduct(product3, 3);
        cart.getCartContents().entrySet().forEach(cartItem -> {
            Product product = cartItem.getKey();
            int quantity = cartItem.getValue();
            double price = product.getPrice();
            double totalPrice = price * quantity;
            System.out.printf("%s, Qty: %d, Price: %.2f, Total: %.2f\n", product.getName(), quantity, price, totalPrice);
        });
        System.out.println("There are " + cart.getTotalProductCount() + " products");
        System.out.println("Your total is " + cart.getTotalPrice());
    }
}
