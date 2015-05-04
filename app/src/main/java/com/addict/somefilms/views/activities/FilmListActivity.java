package com.addict.somefilms.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.addict.model.entites.Film;
import com.addict.somefilms.R;
import com.addict.somefilms.mvp.presenters.FilmsPresenter;
import com.addict.somefilms.mvp.views.FilmsView;
import com.addict.somefilms.views.adapters.FilmsRecyclerAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by CoderHanXin on 2015/05/03.
 */
public class FilmListActivity extends AppCompatActivity implements FilmsView {
    @InjectView(R.id.recyclerView_film_list)
    RecyclerView mRecycler;
//    @InjectView(R.id.toolbar)
//    Toolbar mToolbar;

    private FilmsPresenter mFilmsPresenter;
    private FilmsRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_list);
        ButterKnife.inject(this);

//        setSupportActionBar(mToolbar);

        mRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        mRecycler.setAdapter(new FilmsRecyclerAdapter(null));

        mFilmsPresenter = new FilmsPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFilmsPresenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFilmsPresenter.stop();
    }

    @Override
    public void showFilms(List<Film> filmList) {
        mAdapter = new FilmsRecyclerAdapter(filmList);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void appendFilms(List<Film> filmList) {
        mAdapter.appendFilms(filmList);
    }

    @Override
    public boolean isTheListEmpty() {
        return true;
    }
}
