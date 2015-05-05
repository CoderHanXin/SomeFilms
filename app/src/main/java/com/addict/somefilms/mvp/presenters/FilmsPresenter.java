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
import com.addict.domain.GetFilmsUsecaseController;
import com.addict.model.entites.FilmWrapper;
import com.addict.model.rest.RestFilmSource;
import com.addict.somefilms.mvp.views.FilmsView;
import com.squareup.otto.Subscribe;

/**
 * Created by CoderHanXin on 2015/05/04.
 */
public class FilmsPresenter extends presenter {

    private FilmsView mFilmsView;
    private GetFilmsUsecaseController mGetFilmsUsecaseController;


    public FilmsPresenter(FilmsView filmsView) {
        mFilmsView = filmsView;
        mGetFilmsUsecaseController = new GetFilmsUsecaseController(BusProvider.getUIBusInstance(), RestFilmSource.getInstance());
        mGetFilmsUsecaseController.execute();
    }

    @Subscribe
    public void onTopFilmsReceived(FilmWrapper filmWrapper) {
        if (mFilmsView.isTheListEmpty()) {
            mFilmsView.showFilms(filmWrapper.getSubjects());
        } else {
            mFilmsView.appendFilms(filmWrapper.getSubjects());
        }

    }

    @Override
    public void start() {
        BusProvider.getUIBusInstance().register(this);
    }

    @Override
    public void stop() {
        BusProvider.getUIBusInstance().unregister(this);
    }
}
