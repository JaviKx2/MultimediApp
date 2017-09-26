package com.javikx2.multimediapp.api.tviso.dto.latestnews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("notice")
    @Expose
    private String notice;
    @SerializedName("country")
    @Expose
    private String country;

    /**
     *
     * @return
     * The notice
     */
    public String getNotice() {
        return notice;
    }

    /**
     *
     * @param notice
     * The notice
     */
    public void setNotice(String notice) {
        this.notice = notice;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

}
