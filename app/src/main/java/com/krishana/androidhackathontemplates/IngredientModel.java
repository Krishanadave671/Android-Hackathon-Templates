package com.krishana.androidhackathontemplates;

public class IngredientModel {
    private String name;
    //private  String image;
    private  String value;
    private  String unit;

    public String getName() {
        return name;
    }

//    public String getImage() {
//        return image;
//    }

    public String getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public IngredientModel(String name, String value, String unit) {
        this.name = name;

        this.value = value;
        this.unit = unit;
    }
}
