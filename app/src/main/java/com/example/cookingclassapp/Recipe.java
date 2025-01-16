package com.example.cookingclassapp;

public class Recipe {
    private final String title;
    private final String imageUrl;
    private final String ingredients;
    private final String cookingInstruction;
    private final double time;

    public Recipe(String name, String url, String inputIngredients, String instructions, double clock){
        title = name;
        imageUrl = url;
        ingredients = inputIngredients;
        cookingInstruction = instructions;
        time = clock;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getCookingInstruction() {
        return cookingInstruction;
    }

    public double getTime() {
        return time;
    }
}
