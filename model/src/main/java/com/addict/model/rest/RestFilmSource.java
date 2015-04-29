package com.addict.model.rest;

import com.addict.common.BusProvider;
import com.addict.common.Constants;

import com.addict.model.FilmDataSource;
import com.addict.model.entites.FilmDetail;


import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by CoderHanXin on 2015/04/29.
 */
public class RestFilmSource implements FilmDataSource {
    public static RestFilmSource INSTANCE;
    private final FilmDatabaseApi filmDatabaseApi;

    private RestFilmSource() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.FILM_API_HOST)
                .setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
                .build();

        filmDatabaseApi = restAdapter.create(FilmDatabaseApi.class);
    }

    public static RestFilmSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RestFilmSource();
        }
        return INSTANCE;
    }

    @Override
    public void getFilmDetail(String id) {
        filmDatabaseApi.getFilmDetail(id, Constants.API_KEY ,retrofitCallback);
    }

    public Callback retrofitCallback = new Callback() {
        @Override
        public void success(Object o, Response response) {
            if (o instanceof FilmDetail) {
                FilmDetail filmDetail = (FilmDetail) o;
                BusProvider.getRestBusInstance().post(filmDetail);
            }
        }

        @Override
        public void failure(RetrofitError error) {
            System.out.printf("[DEBUG] RestFilmSource failure - " + error.getMessage());
        }
    };
}
