package com.example.eatit.models;

public class Category {
    private String FoodType;
    private String HomeCookName;
    private String ImageUrl;
    private String Location;
    private String Veg;

    public Category(){}

    @Override
    public String toString() {
        return "Category{" +
                "FoodType='" + FoodType + '\'' +
                ", HomeCookName='" + HomeCookName + '\'' +
                '}';
    }

    public String getFoodType() {
        return FoodType;
    }

    public void setFoodType(String foodType) {
        FoodType = foodType;
    }

    public String getHomeCookName() {
        return HomeCookName;
    }

    public void setHomeCookName(String homeCookName) {
        HomeCookName = homeCookName;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getVeg() {
        return Veg;
    }

    public void setVeg(String veg) {
        Veg = veg;
    }
}
