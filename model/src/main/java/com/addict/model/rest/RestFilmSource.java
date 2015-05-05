/*
 * Copyright (c) 2015 Coder.HanXin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.addict.model.rest;

import com.addict.common.BusProvider;
import com.addict.common.Constants;
import com.addict.model.FilmDataSource;
import com.addict.model.entites.FilmDetail;
import com.addict.model.entites.FilmWrapper;

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
            }else if (o instanceof FilmWrapper) {
                FilmWrapper filmWrapper = (FilmWrapper) o;
                BusProvider.getRestBusInstance().post(filmWrapper);
            }
        }

        @Override
        public void failure(RetrofitError error) {
            System.out.printf("[DEBUG] RestFilmSource failure - " + error.getMessage());
        }
    };

    @Override
    public void getFilmsByPage(int start, int count) {
        filmDatabaseApi.getTopFilmsByPage(Constants.API_KEY, start + "", count + "", retrofitCallback);
    }
}
