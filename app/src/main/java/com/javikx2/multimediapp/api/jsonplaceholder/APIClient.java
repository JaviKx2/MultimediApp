package com.javikx2.multimediapp.api.jsonplaceholder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.javikx2.multimediapp.api.jsonplaceholder.dto.Album;
import com.javikx2.multimediapp.api.jsonplaceholder.dto.AlbumPostResponse;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.javikx2.multimediapp.api.jsonplaceholder.ApiClientConfig.BASE_ENDPOINT;


public class APIClient {
    private static APIService apiService;
    private final static APIClient APIClient = new APIClient();

    static {
        setupAPIClient();
    }

    private APIClient() {
    }

    public static APIClient getInstance() {
        return APIClient;
    }

    private static void setupAPIClient() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(getGson()));
        Retrofit restAdapter = builder.build();
        apiService = restAdapter.create(APIService.class);
    }

    private static Gson getGson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    public void createAlbum(Album album, Callback<AlbumPostResponse> callback){
        apiService.createAlbum(album).enqueue(callback);
    }
}
