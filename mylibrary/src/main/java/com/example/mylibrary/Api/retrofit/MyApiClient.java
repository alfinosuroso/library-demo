    package com.example.mylibrary.Api.retrofit;

    import java.util.concurrent.TimeUnit;

    import okhttp3.OkHttpClient;
    import retrofit2.Retrofit;
    import retrofit2.converter.gson.GsonConverterFactory;

    public class MyApiClient {
        public static Retrofit retrofit;
        public static final String BASE_URL = "https://fakestoreapi.com/";
        public static OkHttpClient client;


        public static Retrofit getRetrofitInstance(){
            if (retrofit == null) {
                client = new OkHttpClient.Builder()
                        .connectTimeout(5, TimeUnit.SECONDS)
                        .readTimeout(5, TimeUnit.SECONDS)
                        .build();

                retrofit = new Retrofit.Builder()
                        .client(client)
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }
