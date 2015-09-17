package com.zapoos.main.zappos.model;

public class Product {
    private String productRating;

    private String price;

    private String asin;

    private String originalPrice;

    private String imageUrl;

    private String productUrl;

    private String map;

    private String productName;

    private String brandName;

    public String getProductRating() {
        return productRating;
    }

    public void setProductRating(String productRating) {
        this.productRating = productRating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "ClassPojo [productRating = " + productRating + ", price = " + price + ", asin = " + asin + ", originalPrice = " + originalPrice + ", imageUrl = " + imageUrl + ", productUrl = " + productUrl + ", map = " + map + ", productName = " + productName + ", brandName = " + brandName + "]";
    }
}