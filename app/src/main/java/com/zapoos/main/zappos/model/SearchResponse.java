package com.zapoos.main.zappos.model;

import java.util.ArrayList;

public class SearchResponse implements Response {

    private ArrayList<Product> results;

    private String totalResults;


    public ArrayList<Product> getResults() {
        return results;
    }

    public void setResults(ArrayList<Product> results) {
        this.results = results;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

}