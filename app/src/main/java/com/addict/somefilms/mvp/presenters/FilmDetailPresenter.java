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

package com.addict.somefilms.mvp.presenters;

import com.addict.common.BusProvider;
import com.addict.domain.GetFilmDetailUsecaseController;
import com.addict.model.entites.FilmDetail;
import com.addict.model.entites.Images;
import com.addict.model.rest.RestFilmSource;
import com.addict.somefilms.mvp.views.FilmDetailView;
import com.squareup.otto.Subscribe;

import java.util.List;

/**
 * Created by CoderHanXin on 2015/04/29.
 */
public class FilmDetailPresenter extends presenter {
    private final FilmDetailView mFilmDetailView;
    private final String mFilmId;

    public FilmDetailPresenter(FilmDetailView filmDetailView, String filmId) {
        mFilmDetailView = filmDetailView;
        mFilmId = filmId;

        new GetFilmDetailUsecaseController(mFilmId,
                BusProvider.getUIBusInstance(), RestFilmSource.getInstance())
                .execute();
    }

    @Override
    public void start() {

        BusProvider.getUIBusInstance().register(this);
    }

    @Override
    public void stop() {

        BusProvider.getUIBusInstance().unregister(this);
    }

    @Subscribe
    public void onFilmDetailReceived(FilmDetail response) {
        showFilmImage(response.getImages());
        showTitle(response.getTitle());
        showSummary(response.getSummary());
        showOriginalTitle(response.getOriginalTitle());
        showGenres(response.getGenres());
        showYear(response.getYear());
        showCountries(response.getCountries());
        showRating(response.getRating().getAverage());
    }

    public void showFilmImage(Images images) {
        mFilmDetailView.setFilmImage(images.getLarge());
    }

    public void showTitle(String title) {
        mFilmDetailView.setTitle(title);
    }

    public void showOriginalTitle(String originalTitle) {
        mFilmDetailView.setOriginalTitle(originalTitle);
    }

    public void showGenres(List<String> genres) {
        String temp = "";
        for (int i = 0; i < genres.size(); i++) {
            temp += genres.get(i);
            if (i != genres.size() - 1) {
                temp += "/";
            }
        }
        mFilmDetailView.setGenres(temp);
    }

    public void showYear(String year) {
        mFilmDetailView.setYear(year);
    }

    public void showCountries(List<String> countries) {
        String temp = "";
        for (int i = 0; i < countries.size(); i++) {
            temp += countries.get(i);
            if (i != countries.size() - 1) {
                temp += "/";
            }
        }
        mFilmDetailView.setCountries(temp);
    }

    public void showSummary(String summary) {
        mFilmDetailView.setSummary(summary);
    }

    public void showRating(Float rating) {
        mFilmDetailView.setRating(rating);
    }
}
