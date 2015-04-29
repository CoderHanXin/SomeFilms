package com.addict.domain;

import com.addict.common.BusProvider;
import com.addict.model.FilmDataSource;
import com.addict.model.entites.FilmDetail;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * Created by CoderHanXin on 2015/04/29.
 */
public class GetFilmDetailUsecaseController implements GetFilmDetailUsecase {

    private final FilmDataSource mFilmDataSource;
    private final String mFilmId;
    private final Bus mUiBus;
    private FilmDetail mFilmDetail;


    public GetFilmDetailUsecaseController(String filmId, Bus uiBus, FilmDataSource dataSource) {
        if (filmId == null) {
            throw new IllegalArgumentException("Film ID can not be null");
        }

        if (uiBus == null) {
            throw new IllegalArgumentException("UiBus can not be null");
        }

        if (dataSource == null) {
            throw new IllegalArgumentException("FilmData source can not be null");
        }

        mFilmId = filmId;
        mUiBus = uiBus;
        mFilmDataSource = dataSource;

        BusProvider.getRestBusInstance().register(this);
    }

    @Override
    public void requestFilmDetail(String filmId) {
        mFilmDataSource.getFilmDetail(filmId);
    }

    @Subscribe
    @Override
    public void onFilmDetailResponse(FilmDetail filmDetail) {
        mFilmDetail = filmDetail;
        mUiBus.post(filmDetail);
        BusProvider.getRestBusInstance().unregister(this);
    }

    @Override
    public void sendFilmDetailToPresenter(FilmDetail response) {
        mUiBus.post(response);
    }

    @Override
    public void execute() {
        requestFilmDetail(mFilmId);
    }
}
