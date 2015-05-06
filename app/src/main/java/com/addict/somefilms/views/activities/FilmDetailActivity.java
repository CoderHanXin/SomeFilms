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
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.addict.somefilms.R;
import com.addict.somefilms.mvp.presenters.FilmDetailPresenter;
import com.addict.somefilms.mvp.views.FilmDetailView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by CoderHanXin on 2015/04/29.
 */
public class FilmDetailActivity extends Activity
        implements FilmDetailView {

    @InjectView(R.id.cover_imageView)
    ImageView mCoverImageView;
    @InjectView(R.id.title_textView)
    TextView mTitleTextView;
    @InjectView(R.id.summary_textView)
    TextView mSummaryTextView;
    @InjectView(R.id.genres_textView)
    TextView mGenresTextView;
    @InjectView(R.id.activity_detail_container)
    FrameLayout mActivityDetailContainer;
    @InjectView(R.id.ratingBar)
    RatingBar mRatingBar;
    @InjectView(R.id.rating_textView)
    TextView mRatingTextView;

    private FilmDetailPresenter mFilmDetailPresenter;
    private LayerDrawable mRatingBarLayer;
    private int mWidth;
    private int mHeight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        ButterKnife.inject(this);

        mRatingBarLayer = (LayerDrawable) mRatingBar.getProgressDrawable();

        setImageSize();

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
    public void setFilmImage(String url) {
        Target t = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                if (bitmap != null) {
                    mCoverImageView.setImageBitmap(bitmap);
                    Palette palette = Palette.from(bitmap).generate();
                    setColorWithPalette(palette);
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        mCoverImageView.setTag(t);
        Picasso.with(this).load(url).resize(mWidth, mHeight).centerCrop().into(t);
    }


    @Override
    public void setTitle(String title) {
        mTitleTextView.setText(title);
    }

    @Override
    public void setSummary(String summary) {
        mSummaryTextView.setText(summary);
    }

    @Override
    public void setGenres(String genres) {
        mGenresTextView.setText(genres);
    }

    @Override
    public void setRating(Float rating) {
        mRatingBar.setRating(rating);
        mRatingTextView.setText(String.valueOf(rating));
    }

    private void setImageSize() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        mWidth = displaymetrics.widthPixels;
        mHeight = height / 3;
    }

    public void setColorWithPalette(Palette palette) {
        if (palette != null) {
            final Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
            final Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
            final Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
            final Palette.Swatch lightMutedSwathc = palette.getLightMutedSwatch();
            final Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
            final Palette.Swatch mutedSwatch = palette.getMutedSwatch();

            final Palette.Swatch backgroundColor = (darkVibrantSwatch != null) ? darkVibrantSwatch : darkMutedSwatch;
            final Palette.Swatch titleColor = (lightVibrantSwatch != null) ? lightVibrantSwatch : lightMutedSwathc;
            setBackgroundColors(backgroundColor);
            setTitleColors(titleColor);
        }
    }

    private void setBackgroundColors(Palette.Swatch swatch) {
        mActivityDetailContainer.setBackgroundColor(swatch.getRgb());
        mTitleTextView.setTextColor(swatch.getRgb());
        mSummaryTextView.setTextColor(swatch.getTitleTextColor());
        mGenresTextView.setTextColor(swatch.getTitleTextColor());
        mRatingTextView.setTextColor(swatch.getTitleTextColor());
        mRatingBarLayer.getDrawable(2).setColorFilter(swatch.getTitleTextColor(), PorterDuff.Mode.SRC_ATOP);

    }

    private void setTitleColors(Palette.Swatch swatch) {
        mTitleTextView.setBackgroundColor(swatch.getRgb());
    }
}
