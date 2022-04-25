package com.example.demoretrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StoreClient {
    @GET("products/{id}")
    Call<Product> getProducts(
            @Path("id") String id
    );
}
