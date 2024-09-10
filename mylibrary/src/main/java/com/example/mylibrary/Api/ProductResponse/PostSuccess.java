package com.example.mylibrary.Api.ProductResponse;

import com.google.gson.annotations.SerializedName;

public class PostSuccess {
    @SerializedName("id")
    int id;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}
