package com.javikx2.multimediapp.api.tviso.dto.authtoken;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthTokenResponse {

    @SerializedName("auth_token")
    @Expose
    private String authToken;
    @SerializedName("auth_expires_date")
    @Expose
    private Integer authExpiresDate;
    @SerializedName("error")
    @Expose
    private Integer error;

    /**
     *
     * @return
     * The authToken
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     *
     * @param authToken
     * The auth_token
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     *
     * @return
     * The authExpiresDate
     */
    public Integer getAuthExpiresDate() {
        return authExpiresDate;
    }

    /**
     *
     * @param authExpiresDate
     * The auth_expires_date
     */
    public void setAuthExpiresDate(Integer authExpiresDate) {
        this.authExpiresDate = authExpiresDate;
    }

    /**
     *
     * @return
     * The error
     */
    public Integer getError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    public void setError(Integer error) {
        this.error = error;
    }

}