package com.example.android3hw4.data;

import com.google.gson.annotations.SerializedName;

public class MokerModel {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("user")
    private int user;

    @SerializedName("group")
    private int group;

    public MokerModel(String title, String content, int user, int group) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getUser() {
        return user;
    }

    public int getGroup() {
        return group;
    }

   
}
