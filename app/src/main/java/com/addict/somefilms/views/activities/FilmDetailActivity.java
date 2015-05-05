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

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.addict.somefilms.R;
import com.addict.somefilms.mvp.presenters.FilmDetailPresenter;
import com.addict.somefilms.mvp.views.FilmDetailView;
import com.squareup.picasso.Picasso;

/**
 * Created by CoderHanXin on 2015/04/29.
 */
public class FilmDetailActivity extends Activity implements FilmDetailView {

    private FilmDetailPresenter mFilmDetailPresenter;
    private TextView mContentTextView;
    private ImageView mImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        mContentTextView = (TextView) findViewById(R.id.textView_content);
        mImage = (ImageView) findViewById(R.id.image);
        String filmId = getIntent().getStringExtra(FilmListActivity.EXTRA_FILM_ID);
        mFilmDetailPresenter = new FilmDetailPresenter(this, filmId);
    }


    @Override
    protected void onStop() {

        super.onStop();
        mFilmDetailPresenter.stop();
    }

    @Override
    protected void onStart() {

        super.onStart();
        mFilmDetailPresenter.start();
    }

    @Override
    public void setContent(String content) {
        mContentTextView.setText(content);
    }

    @Override
    public void setFilmImage(String url) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        mImage.setLayoutParams(new LinearLayout.LayoutParams(width,height/3));
        Picasso.with(this).load(url).fit().centerCrop().into(mImage);
    }
}
