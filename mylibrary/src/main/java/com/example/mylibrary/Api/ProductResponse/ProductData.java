package com.example.mylibrary.Api.ProductResponse;

import com.google.gson.annotations.SerializedName;

public class ProductData {

    @SerializedName("id")
    int id;

    @SerializedName("title")
    String title;

    @SerializedName("price")
    double price;

    @SerializedName("description")
    String description;

    @SerializedName("category")
    String category;

    @SerializedName("image")
    String image;

    @SerializedName("rating")
    Rating rating;


    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getImage() {
        return image;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
    public Rating getRating() {
        return rating;
    }

}
