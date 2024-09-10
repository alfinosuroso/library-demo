package com.alfinosuroso.mylibrary.Api.retrofit;


import com.alfinosuroso.mylibrary.Api.ProductResponse.ProductData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyAPIService {
    @GET("products")
    Call<List<ProductData>> getAllProducts();

    @POST("products")
    Call<ProductData> addProduct(@Body ProductData productData);
}
