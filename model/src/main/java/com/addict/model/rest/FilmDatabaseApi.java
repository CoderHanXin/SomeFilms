package com.addict.model.rest;

import com.addict.model.entites.FilmDetail;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by CoderHanXin on 2015/04/29.
 */
public interface FilmDatabaseApi {
    @GET("/v2/movie/subject/{id}")
    void getFilmDetail(@Path("id") String id, @Query("apikey") String apiKey, Callback<FilmDetail> callback);
}
