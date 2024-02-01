package com.example.caloriecounter;

public class Product {
    private String productName;
    private String productProteins;
    private String productFats;
    private String productCarbs;
    private String productAmount;


    public String getProductName() {
        return productName;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public String getProductProteins() {
        return productProteins;
    }

    public String getProductFats() {
        return productFats;
    }

    public String getProductCarbs() {
        return productCarbs;
    }

    public String getProductCalories() {
        return productCalories;
    }

    private String productCalories;

    public Product(String productName, String productProteins, String productFats, String productCarbs, String productAmount, String productCalories) {
        this.productName = productName;
        this.productProteins = productProteins;
        this.productFats = productFats;
        this.productCarbs = productCarbs;
        this.productAmount = productAmount;
        this.productCalories = productCalories;
    }

    public Product(String productName, String productProteins, String productFats, String productCarb, String productCalories) {
        this.productName = productName;
        this.productProteins = productProteins;
        this.productFats = productFats;
        this.productCarbs = productCarb;
        this.productCalories = productCalories;
    }
}
