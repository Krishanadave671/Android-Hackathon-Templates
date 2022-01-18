package com.krishana.androidhackathontemplates;

public class FoodModel {
    private String title;
    private String image;
    private int id;





    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public int getId() { return id; }

    public FoodModel(String title, String image, int id) {
        this.title = title;
        this.image = image;
        this.id = id;
    }
}
