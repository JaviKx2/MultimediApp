package com.javikx2.multimediapp.api.jsonplaceholder.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumPostResponse {

    @SerializedName("id")
    @Expose
    private Integer id;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

}
