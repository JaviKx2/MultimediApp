package com.javikx2.multimediapp.api.tviso.dto.latestnews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("host")
    @Expose
    private Integer host;

    /**
     *
     * @return
     * The code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return
     * The host
     */
    public Integer getHost() {
        return host;
    }

    /**
     *
     * @param host
     * The host
     */
    public void setHost(Integer host) {
        this.host = host;
    }

}