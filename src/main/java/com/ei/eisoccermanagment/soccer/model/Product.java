package com.ei.eisoccermanagment.soccer.model;

public class Product {
    private int productId;
    private String name;
    private double price;
    private String description;
    private String color;
    private int categoryId;
    private String categoryName;


    public Product() {
    }

    public Product(int productId, String name, double price, String description, String color, int categoryId, String categoryName) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.color = color;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
