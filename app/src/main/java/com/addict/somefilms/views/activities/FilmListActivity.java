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
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    public final static String EXTRA_FILM_ID = "film_id";

    private FilmsPresenter mFilmsPresenter;
    private FilmsRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_list);
        ButterKnife.inject(this);

        initializeToolbar();

        initializeRecycler();

        mFilmsPresenter = new FilmsPresenter(this);
    }

    private void initializeToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    private void initializeRecycler() {
        mRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        mRecycler.addOnScrollListener(recyclerScrollListener);
    }

    private void initializeListener() {
        mAdapter.setOnItemClickListener(new FilmsRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(FilmListActivity.this, FilmDetailActivity.class);
                if (mAdapter.getFilmList().size() > 0) {
                    intent.putExtra(EXTRA_FILM_ID, mAdapter.getFilmList().get(position).getId());
                    startActivity(intent);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
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
        public boolean flag;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

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
