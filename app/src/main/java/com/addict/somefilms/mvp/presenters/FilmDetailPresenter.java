package com.addict.somefilms.mvp.presenters;

import com.addict.common.BusProvider;
import com.addict.domain.GetFilmDetailUsecaseController;
import com.addict.model.entites.FilmDetail;
import com.addict.model.rest.RestFilmSource;
import com.addict.somefilms.mvp.views.FilmDetailView;
import com.squareup.otto.Subscribe;

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
        showContent(response.getTitle());
    }

    public void showContent(String content) {
        mFilmDetailView.setContent(content);
    }
}
