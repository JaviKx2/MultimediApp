package com.javikx2.multimediapp.api.jsonplaceholder;


import com.javikx2.multimediapp.api.jsonplaceholder.dto.Album;
import com.javikx2.multimediapp.api.jsonplaceholder.dto.AlbumPostResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {
    @POST(ApiClientConfig.ALBUMS_ENDPOINT)
    Call<AlbumPostResponse> createAlbum(@Body Album album);
}

