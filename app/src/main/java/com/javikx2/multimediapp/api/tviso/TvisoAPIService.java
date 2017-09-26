package com.javikx2.multimediapp.api.tviso;

import com.javikx2.multimediapp.api.tviso.dto.authtoken.AuthTokenResponse;
import com.javikx2.multimediapp.api.tviso.dto.catalogue.CatalogueResponse;
import com.javikx2.multimediapp.api.tviso.dto.latestnews.LatestNewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TvisoAPIService {
    @GET(TvisoApiClientConfig.AUTH_TOKEN_ENDPOINT)
    Call<AuthTokenResponse> getAuthToken(
            @Query("id_api") int id_api,
            @Query("secret") String secret
    );

    @GET(TvisoApiClientConfig.LATEST_NEWS_ENDPOINT)
    Call<LatestNewsResponse> getLatestNews(
            @Query("auth_token") String auth_token
    );

    @GET(TvisoApiClientConfig.CATALOGUE_ENDPOINT)
    Call<CatalogueResponse> getCatalogueByTitle(
            @Query("auth_token") String auth_token,
            @Query("q") String q
    );

    /*@GET(CATALOGUE_FULL_INFO_ENDPOINT)
    Call<CatalogueFullInfoResponse> getCatalogueByTitle(
            @Query("auth_token") String auth_token,
            @Query("idm") String idm,
            @Query("mediaType") String mediatype
    );*/
}
