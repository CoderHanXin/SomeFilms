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

import com.addict.model.entites.FilmDetail;
import com.addict.model.entites.FilmWrapper;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by CoderHanXin on 2015/04/29.
 */
public interface FilmDatabaseApi {
    @GET("/v2/movie/subject/{id}")
    void getFilmDetail(
            @Path("id") String id,
            @Query("apikey") String apiKey,
            Callback<FilmDetail> callback
    );

    @GET("/v2/movie/top250")
    void getTopFilmsByPage(
            @Query("apikey") String apiKep,
            @Query("start") String start,
            @Query("count") String count,
            Callback<FilmWrapper> callback
    );
}
