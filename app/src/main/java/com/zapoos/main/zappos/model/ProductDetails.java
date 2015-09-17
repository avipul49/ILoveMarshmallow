package com.zapoos.main.zappos.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vipulmittal on 14/09/15.
 */
public class ProductDetails implements Response {
    private String asin;

    private String defaultProductType;

    private String description;

    private ArrayList<ChildAsins> childAsins;

    private String[] genders;

    private String productName;

    private String brandName;

    private String defaultImageUrl;

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getDefaultProductType() {
        return defaultProductType;
    }

    public void setDefaultProductType(String defaultProductType) {
        this.defaultProductType = defaultProductType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<ChildAsins> getChildAsins() {
        return childAsins;
    }

    public void setChildAsins(ArrayList<ChildAsins> childAsins) {
        this.childAsins = childAsins;
    }

    public String[] getGenders() {
        return genders;
    }

    public void setGenders(String[] genders) {
        this.genders = genders;
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

    public String getDefaultImageUrl() {
        return defaultImageUrl;
    }

    public void setDefaultImageUrl(String defaultImageUrl) {
        this.defaultImageUrl = defaultImageUrl;
    }

    public void filter() {
        ArrayList<ChildAsins> asins = new ArrayList<>();
        for (ChildAsins childAsins1 : childAsins) {
            if (childAsins1.isOnSale()) {
                asins.add(childAsins1);
            }
        }
        childAsins.clear();
        childAsins.addAll(asins);
    }

    HashMap<String, ArrayList<ChildAsins>> map = new HashMap<>();

    public HashMap<String, ArrayList<ChildAsins>> getMap() {
        return map;
    }

    public void collectChildAsins() {
        for (ChildAsins childAsins1 : childAsins) {
            if (childAsins1.getColor() != null && !childAsins1.getColor().isEmpty()) {
                ArrayList<ChildAsins> asins = map.get(childAsins1.getColor());
                if (asins == null) {
                    asins = new ArrayList<>();
                    map.put(childAsins1.getColor(), asins);
                }
                asins.add(childAsins1);
            }
        }
    }

    public String getGendersString() {
        String genders = "";
        for (String gender : this.genders) {
            genders += gender + ", ";
        }
        return genders.substring(0, genders.length() - 2);
    }

}
