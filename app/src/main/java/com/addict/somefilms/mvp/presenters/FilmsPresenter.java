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
    private void onTopFilmsReceived(FilmWrapper filmWrapper) {
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

    }
}
