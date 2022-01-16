package com.krishana.androidhackathontemplates;

public class RecyclerViewData {
    String item ;
    String expiryDate;
    int quantity;

    public RecyclerViewData(String item, String expiryDate,int quantity) {
        this.item = item;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

    public RecyclerViewData()
    {
        //this is needed for firestore database to create recyclerview
    }

    public String getItem() {
        return item;
    }
    public String getExpiryDate() {
        return expiryDate;
    }
    public int getQuantity() {
        return quantity;
    }
}
