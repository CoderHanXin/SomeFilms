package com.addict.domain;

import com.addict.model.entites.FilmDetail;

/**
 * Created by CoderHanXin on 2015/04/29.
 */
public interface GetFilmDetailUsecase extends Usecase {

    public void requestFilmDetail(String filmId);

    public void onFilmDetailResponse(FilmDetail reponse);

    public void sendFilmDetailToPresenter(FilmDetail response);
}
