package com.krishana.androidhackathontemplates;

public class RecipeModel1 {
    private String number;
    private  String step;

    public String getNumber() {
        return number;
    }

    public String getStep() {
        return step;
    }

    public RecipeModel1(String number, String step) {
        this.number = number;
        this.step = step;
    }
}
