package com.addict.domain;

import com.addict.model.entites.FilmWrapper;

/**
 * Created by CoderHanXin on 2015/05/04.
 */
public interface GetFilmsUsecase extends Usecase{
    public void requestTopFilms();

    public void sendFilmsToPresenter(FilmWrapper filmWrapper);
}
