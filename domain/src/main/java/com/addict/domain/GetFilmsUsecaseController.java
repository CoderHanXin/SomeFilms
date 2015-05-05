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

package com.addict.domain;

import com.addict.common.BusProvider;
import com.addict.common.Constants;
import com.addict.model.FilmDataSource;
import com.addict.model.entites.FilmWrapper;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * Created by CoderHanXin on 2015/05/04.
 */
public class GetFilmsUsecaseController implements GetFilmsUsecase {

    private final FilmDataSource mFilmDataSource;
    private final Bus mUiBus;
    private int mCurrentStart = 0;

    public GetFilmsUsecaseController(Bus uiBus, FilmDataSource dataSource) {
        mFilmDataSource = dataSource;
        mUiBus = uiBus;
        BusProvider.getRestBusInstance().register(this);
    }

    @Override
    public void requestTopFilms() {
        mFilmDataSource.getFilmsByPage(mCurrentStart, Constants.COUNT_PER_PAGE);
    }

    @Subscribe
    @Override
    public void sendFilmsToPresenter(FilmWrapper response) {
        mUiBus.post(response);
        BusProvider.getRestBusInstance().unregister(this);
    }

    @Override
    public void execute() {
        requestTopFilms();
        mCurrentStart += Constants.COUNT_PER_PAGE;
    }
}
