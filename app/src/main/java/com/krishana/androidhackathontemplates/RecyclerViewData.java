package com.krishana.androidhackathontemplates;

public class RecyclerViewData {
    String item ;
    long expiryDate;
    String category;

    public RecyclerViewData(String item, long expiryDate,String category) {
        this.item = item;
        this.expiryDate = expiryDate;
        this.category = category;
    }

    public RecyclerViewData()
    {
        //this is needed for firestore database to create recyclerview
    }

    public String getItem() {
        return item;
    }
    public long getExpiryDate() {
        return expiryDate;
    }
    public String getCategory() {
        return category;
    }
}
