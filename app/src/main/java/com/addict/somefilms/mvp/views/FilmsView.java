package com.addict.somefilms.mvp.views;

import com.addict.model.entites.Film;

import java.util.List;

/**
 * Created by CoderHanXin on 2015/05/04.
 */
public interface FilmsView extends MvpView {
    void showFilms(List<Film> filmList);

    void appendFilms(List<Film> filmList);

    boolean isTheListEmpty();
}
