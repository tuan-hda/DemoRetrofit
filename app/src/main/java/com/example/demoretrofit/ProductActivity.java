package com.example.demoretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductActivity extends AppCompatActivity {
    GridView gridView;
    Retrofit retrofit;
    StoreClient storeClient;
    ArrayList<Product> products;
    ProductCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        // init UI views
        initUiViews();

        // create retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create new array list of product
        products = new ArrayList<>();

        // implement adapter
        implementGridViewAdapter();

        // get Store Client
        storeClient = retrofit.create(StoreClient.class);

        // get products
        getProducts();
    }

    private void getProducts() {
        Call<Product> call = storeClient.getProducts("1");

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Toast.makeText(ProductActivity.this, "", Toast.LENGTH_SHORT).show();
                products.add(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }

    private void implementGridViewAdapter() {
        adapter = new ProductCustomAdapter(this, products);
        gridView.setAdapter(adapter);
    }

    private void initUiViews() {
        gridView = findViewById(R.id.gridView);
    }
}