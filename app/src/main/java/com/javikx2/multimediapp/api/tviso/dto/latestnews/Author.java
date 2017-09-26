package com.javikx2.multimediapp.api.tviso.dto.latestnews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("nick")
    @Expose
    private String nick;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("img_user")
    @Expose
    private String imgUser;

    /**
     *
     * @return
     * The uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     *
     * @param uid
     * The uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     *
     * @return
     * The nick
     */
    public String getNick() {
        return nick;
    }

    /**
     *
     * @param nick
     * The nick
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     *
     * @return
     * The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @param fullName
     * The full_name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     *
     * @return
     * The imgUser
     */
    public String getImgUser() {
        return imgUser;
    }

    /**
     *
     * @param imgUser
     * The img_user
     */
    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }

}