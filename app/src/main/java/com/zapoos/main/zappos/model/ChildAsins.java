package com.zapoos.main.zappos.model;

import java.util.Map;

public class ChildAsins {
//    private Sizing sizing;

    private boolean onSale;

    private String price;

    private String asin;

    private String originalPrice ;

    private String imageUrl;

    private String color;

    private Map map;

    private String[] genders;

    private String[] upc;

    private String onHand;
//
//    public Sizing getSizing() {
//        return sizing;
//    }
//
//    public void setSizing(Sizing sizing) {
//        this.sizing = sizing;
//    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

//    public Map getMap() {
//        return map;
//    }

//    public void setMap(Map map) {
//        this.map = map;
//    }

    public String[] getGenders() {
        return genders;
    }

    public void setGenders(String[] genders) {
        this.genders = genders;
    }

    public String[] getUpc() {
        return upc;
    }

    public void setUpc(String[] upc) {
        this.upc = upc;
    }

    public String getOnHand() {
        return onHand;
    }

    public void setOnHand(String onHand) {
        this.onHand = onHand;
    }

    @Override
    public String toString() {
        return "ClassPojo [onSale = " + onSale + ", price = " + price + ", asin = " + asin + ", originalPrice = " + originalPrice + ", imageUrl = " + imageUrl + ", color = " + color + ", map = " + map + ", genders = " + genders + ", upc = " + upc + ", onHand = " + onHand + "]";
    }
}

