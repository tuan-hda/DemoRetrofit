package com.example.demoretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<Post> adapter;
    ArrayList<Post> postArrayList;
    JsonPlaceholderAPI jsonPlaceholderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postArrayList = new ArrayList<>();

        // init UI views
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, postArrayList);
        listView.setAdapter(adapter);

        // Tạo Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Lấy client
        jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

        //getAllPosts();

        //getSinglePost();

        //createPost();

        //updatePost();

        //deletePost();

        //queryPost();
    }

    private void getAllPosts() {
        Call<ArrayList<Post>> call = jsonPlaceholderAPI.getPosts();

        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                // Return nếu không thành công
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Unsuccessful! Response code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                postArrayList.addAll(response.body());
                Toast.makeText(MainActivity.this, "Successfully! Count: " + postArrayList.size() , Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed! Error: " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSinglePost() {
        // Thực hiện GET một post với id = 4
        Call<Post> call = jsonPlaceholderAPI.getSinglePost("posts/5");
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                postArrayList.add(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
            }
        });
    }

    private void queryPost() {
        Call<ArrayList<Post>> call = jsonPlaceholderAPI.getPosts(null);
        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                postArrayList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {

            }
        });
    }

    private void deletePost() {
        Call<Void> call = jsonPlaceholderAPI.deletePost(4);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(MainActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


    private void updatePost() {
        Post post = new Post(13, "New title", null);

        Call<Post> call = jsonPlaceholderAPI.patchPost(2, post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                postArrayList.add(response.body());
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    private void createPost() {
        Post post = new Post(15,"Post title", "Post body");

        Call<Post> call = jsonPlaceholderAPI.createPost(10, "Title 2", "Body 2");
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                postArrayList.add(response.body());
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }
}