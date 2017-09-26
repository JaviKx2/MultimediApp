package com.javikx2.multimediapp.api.jsonplaceholder.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {
    @Expose
    @SerializedName("userId")
    private Integer userId;
    @Expose
    @SerializedName("id")
    private Integer id;
    @Expose
    @SerializedName("title")
    private String title;

    public Album(String title) {
        this.userId = 1;
        this.id = 101;
        this.title = title;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
