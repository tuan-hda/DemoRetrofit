package com.example.demoretrofit;

public class Post {
    private int userId, id;
    private String title, body;

    public Post(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    @Override
    public String toString() {
        return "userID: " + userId + "\n" +
                "id: " + id + "\n" +
                "title: " + title + "\n" +
                "body: " + body;
    }
}
