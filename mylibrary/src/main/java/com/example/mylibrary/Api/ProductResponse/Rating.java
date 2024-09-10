package com.example.mylibrary.Api.ProductResponse;

import com.google.gson.annotations.SerializedName;


public class Rating {

    @SerializedName("rate")
    double rate;

    @SerializedName("count")
    int count;


    public void setRate(double rate) {
        this.rate = rate;
    }
    public double getRate() {
        return rate;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }

}
