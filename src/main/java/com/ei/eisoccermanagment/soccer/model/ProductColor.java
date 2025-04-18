package com.ei.eisoccermanagment.soccer.model;

public class ProductColor {
    private int colorId;
    private String colorName;
    private int numColorProducts;

    public ProductColor(int colorId, String colorName, int numColorProducts) {
        this.colorId = colorId;
        this.colorName = colorName;
        this.numColorProducts = numColorProducts;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getNumColorProducts() {
        return numColorProducts;
    }

    public void setNumColorProducts(int numColorProducts) {
        this.numColorProducts = numColorProducts;
    }

    @Override
    public String toString() {
        return "ProductColor{" +
                "colorId=" + colorId +
                ", colorName='" + colorName + '\'' +
                ", numColorProducts=" + numColorProducts +
                '}';
    }
}
