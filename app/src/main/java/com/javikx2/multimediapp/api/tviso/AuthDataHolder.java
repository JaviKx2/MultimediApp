package com.javikx2.multimediapp.api.tviso;

public class AuthDataHolder {

    private String authToken;
    private String userToken;

    private static AuthDataHolder holder;

    private AuthDataHolder(){}
    
    public static AuthDataHolder getInstance() {
        if(holder != null) return holder;
        synchronized(AuthDataHolder.class) {
            if (holder == null) {
                holder = new AuthDataHolder();
            }
        }
        return holder;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserToken() {
        return this.userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
