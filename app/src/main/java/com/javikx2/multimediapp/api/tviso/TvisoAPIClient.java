package com.javikx2.multimediapp.api.tviso;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.javikx2.multimediapp.api.tviso.dto.authtoken.AuthTokenResponse;
import com.javikx2.multimediapp.api.tviso.dto.catalogue.CatalogueResponse;
import com.javikx2.multimediapp.api.tviso.dto.latestnews.LatestNewsResponse;
import com.javikx2.multimediapp.api.tviso.exception.TvisoApiClientException;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.javikx2.multimediapp.api.tviso.TvisoApiClientConfig.API_SECRET;
import static com.javikx2.multimediapp.api.tviso.TvisoApiClientConfig.BASE_ENDPOINT;
import static com.javikx2.multimediapp.api.tviso.TvisoApiClientConfig.ID_API;

public class TvisoAPIClient {
    private static TvisoAPIService apiService;
    private final static TvisoAPIClient tvisoAPIClient = new TvisoAPIClient();

    static {
        setupTvisoAPIClient();
    }

    private TvisoAPIClient() {
    }

    public static TvisoAPIClient getInstance() {
        return tvisoAPIClient;
    }

    private static void setupTvisoAPIClient() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(getGson()));
        Retrofit restAdapter = builder.build();
        apiService = restAdapter.create(TvisoAPIService.class);
    }

    private static Gson getGson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    public void getAuthToken(Callback<AuthTokenResponse> callback) throws TvisoApiClientException {
        apiService.getAuthToken(ID_API, API_SECRET).enqueue(callback);
    }

    public void getLatestNews(Callback<LatestNewsResponse> callback) throws TvisoApiClientException {
        String authToken = AuthDataHolder.getInstance().getAuthToken();
        apiService.getLatestNews(authToken).enqueue(callback);
    }

    public void getCatalogueByTitle(String title, Callback<CatalogueResponse> callback){
        String authToken = AuthDataHolder.getInstance().getAuthToken();
        apiService.getCatalogueByTitle(authToken, title).enqueue(callback);
    }
}
