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

package com.addict.somefilms.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import com.addict.model.entites.Film;
import com.addict.model.entites.FilmWrapper;
import com.addict.somefilms.R;
import com.addict.somefilms.mvp.presenters.FilmsPresenter;
import com.addict.somefilms.mvp.views.FilmsView;
import com.addict.somefilms.views.adapters.FilmsRecyclerAdapter;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by CoderHanXin on 2015/05/03.
 */
public class FilmListActivity extends AppCompatActivity implements FilmsView {
    @InjectView(R.id.recyclerView_film_list)
    RecyclerView mRecycler;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.film_list_progressBar)
    ProgressBar mProgressBar;

    private final static String BUNDLE_FILM_WRAPPER = "film_wrapper";
    public final static String EXTRA_FILM = "film";

    private FilmsPresenter mFilmsPresenter;
    private FilmsRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_list);
        ButterKnife.inject(this);

        initializeToolbar();
        initializeRecycler();

        if (savedInstanceState == null) {
            mFilmsPresenter = new FilmsPresenter(this);
        } else {
            FilmWrapper filmWrapper = (FilmWrapper) savedInstanceState.getSerializable(BUNDLE_FILM_WRAPPER);
            mFilmsPresenter.onTopFilmsReceived(filmWrapper);
        }

    }

    private void initializeToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    private void initializeRecycler() {
        mRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        mRecycler.setHasFixedSize(true);
        mRecycler.addOnScrollListener(recyclerScrollListener);
    }

    private void initializeListener() {
        mAdapter.setOnItemClickListener(new FilmsRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(FilmListActivity.this, FilmDetailActivity.class);
                if (mAdapter.getFilmList().size() > 0) {
                    intent.putExtra(EXTRA_FILM, mAdapter.getFilmList().get(position));
                    startActivity(intent);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mAdapter != null) {
            outState.putSerializable(BUNDLE_FILM_WRAPPER, new FilmWrapper(mAdapter.getFilmList()));
        }
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
    public void showLoading() {

        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingLabel() {

        Snackbar moreFilmsSnackBar = Snackbar.with(this)
                .text(R.string.message_loading_more_films)
                .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                .colorResource(R.color.primary);
        SnackbarManager.show(moreFilmsSnackBar);
    }

    @Override
    public void hideLoadingLabel() {
        SnackbarManager.dismiss();
    }

    @Override
    public void showFilms(List<Film> filmList) {
        mAdapter = new FilmsRecyclerAdapter(filmList);
        mRecycler.setAdapter(mAdapter);
        initializeListener();
    }

    @Override
    public void appendFilms(List<Film> filmList) {
        mAdapter.appendFilms(filmList);
    }

    @Override
    public boolean isTheListEmpty() {
        return (mAdapter == null) || mAdapter.getFilmList().isEmpty();
    }

    private RecyclerView.OnScrollListener recyclerScrollListener = new RecyclerView.OnScrollListener() {
        private boolean flag;
        private boolean firstLoading = true;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);


            int visibleItemCount    = mRecycler.getLayoutManager().getChildCount();
            int totalItemCount      = mRecycler.getLayoutManager().getItemCount();
            int firstVisibleItems    = ((GridLayoutManager) mRecycler.getLayoutManager())
                    .findFirstVisibleItemPosition();

            if (firstLoading) {
                firstLoading = false;
                return;
            }

            if((visibleItemCount + firstVisibleItems) >= totalItemCount && !mFilmsPresenter.isLoading()) {
                mFilmsPresenter.onEndless();
            }

            // Is scrolling up
            if (dy > 10) {

                if (!flag) {

                    hideToolbar();
                    flag = true;
                }

                // Is scrolling down
            } else if (dy < -10) {

                if (flag) {

                    showToolbar();
                    flag = false;
                }
            }

        }
    };

    private void hideToolbar() {
        mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
    }

    private void showToolbar() {
        mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    }
}
