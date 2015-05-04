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
        mFilmDataSource.getFilmsByPage(mCurrentStart);
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
