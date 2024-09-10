package com.example.mylibrary.Models;

public class MethodPayment {
    private String name;
    private int image;
    public MethodPayment(String name, int image) {
        this.name = name;
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public int getImage() {
        return image;
    }
}