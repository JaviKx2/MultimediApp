
package com.javikx2.multimediapp.api.tviso.dto.catalogue;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("backdrop")
    @Expose
    private String backdrop;
    @SerializedName("country")
    @Expose
    private String country;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Images() {
    }

    public Images(String backdrop, String country){
        this.backdrop = backdrop;
        this.country = country;
    }

    /**
     * 
     * @param poster
     * @param country
     * @param backdrop
     */
    public Images(String poster, String backdrop, String country) {
        this.poster = poster;
        this.backdrop = backdrop;
        this.country = country;
    }

    /**
     * 
     * @return
     *     The poster
     */
    public String getPoster() {
        return poster;
    }

    /**
     * 
     * @param poster
     *     The poster
     */
    public void setPoster(String poster) {
        this.poster = poster;
    }

    /**
     * 
     * @return
     *     The backdrop
     */
    public String getBackdrop() {
        return backdrop;
    }

    /**
     * 
     * @param backdrop
     *     The backdrop
     */
    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

}
