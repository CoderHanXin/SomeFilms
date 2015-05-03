package com.addict.somefilms.mvp.views;

/**
 * Created by CoderHanXin on 2015/04/29.
 */
public interface FilmDetailView extends MvpView {
    public void setContent(String content);

    public void setFilmImage(String url);
}
