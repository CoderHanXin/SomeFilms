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
import com.addict.model.entites.Crew;
import com.addict.model.entites.FilmDetail;
import com.addict.model.rest.RestFilmSource;
import com.addict.somefilms.mvp.views.FilmDetailView;
import com.squareup.otto.Subscribe;

import java.util.List;

/**
 * Created by CoderHanXin on 2015/04/29.
 */
public class FilmDetailPresenter extends presenter {
    private final GetFilmDetailUsecaseController mFilmDetailController;
    private final FilmDetailView mFilmDetailView;
    private final String mFilmId;

    public FilmDetailPresenter(FilmDetailView filmDetailView, String filmId) {
        mFilmDetailView = filmDetailView;
        mFilmId = filmId;

        mFilmDetailController = new GetFilmDetailUsecaseController(mFilmId,
                BusProvider.getUIBusInstance(), RestFilmSource.getInstance());
    }

    @Override
    public void start() {
        mFilmDetailController.execute();
        BusProvider.getUIBusInstance().register(this);
    }

    @Override
    public void stop() {

        BusProvider.getUIBusInstance().unregister(this);
    }

    @Subscribe
    public void onFilmDetailReceived(FilmDetail response) {
        showSummary(response.getSummary());
        showCountries(response.getCountries());
        showCasts(response.getCasts());
        showDirectors(response.getDirectors());
    }

    public void showGenres(List<String> genres) {
        mFilmDetailView.setGenres(genres);
    }

    public void showCountries(List<String> countries) {
        mFilmDetailView.setCountries(countries);
    }

    public void showSummary(String summary) {
        mFilmDetailView.setSummary(summary);
    }

    public void showCasts(List<Crew> crewList) {
        mFilmDetailView.setCasts(crewList);
    }

    public void showDirectors(List<Crew> crewList) {
        mFilmDetailView.setDirectors(crewList);
    }
}
