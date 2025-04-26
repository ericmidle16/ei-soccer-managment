package com.ei.eisoccermanagment.soccer.model;

public class Product {
    private int productId;
    private String name;
    private double price;
    private String description;
    private int categoryId;
    private String categoryName;
    private int colorId;
    private String colorName;


    public Product() {
    }

    public Product(int productId, String name, double price, String description, int categoryId, String categoryName, int colorId, String colorName) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.colorId = colorId;
        this.colorName = colorName;
    }

    public Product(int productId, String name, double price, String description, int categoryId, int colorId) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.colorId = colorId;
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
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPrice(String price) {
        try{
            double priceTemp = Double.parseDouble(price);
            if(priceTemp <= 0){
                throw new IllegalArgumentException("Price must be positive");
            }
            this.price = priceTemp;
        } catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Price must be positive");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description == null || description.isEmpty()){
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryId(String categoryId) {
        try{
            int categoryTemp = Integer.parseInt(categoryId);
            this.categoryId = categoryTemp;
        } catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Error");
        }
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public void setColorId(String colorId) {
        try{
            int colorTemp = Integer.parseInt(colorId);
            this.colorId = colorTemp;
        } catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Error");
        }
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", colorId=" + colorId +
                ", colorName='" + colorName + '\'' +
                '}';
    }
}
