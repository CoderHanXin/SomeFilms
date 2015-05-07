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
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.addict.somefilms.R;
import com.addict.somefilms.model.ColorScheme;
import com.addict.somefilms.mvp.presenters.FilmDetailPresenter;
import com.addict.somefilms.mvp.views.FilmDetailView;
import com.addict.somefilms.views.customs.FilmDetailCardLayout;
import com.addict.somefilms.views.customs.FilmDetailInfoLayout;
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
    @InjectView(R.id.activity_detail_container)
    FrameLayout mActivityDetailContainer;
    @InjectView(R.id.ratingBar)
    RatingBar mRatingBar;
    @InjectView(R.id.rating_textView)
    TextView mRatingTextView;
    @InjectView(R.id.details_original_title)
    FilmDetailInfoLayout mDetailsOriginalTitle;
    @InjectView(R.id.details_genres)
    FilmDetailInfoLayout mDetailsGenres;
    @InjectView(R.id.details_year)
    FilmDetailInfoLayout mDetailsYear;
    @InjectView(R.id.details_countries)
    FilmDetailInfoLayout mDetailsCountries;
    @InjectView(R.id.card_content)
    LinearLayout mCardContent;
    @InjectView(R.id.film_detail_card_details)
    FilmDetailCardLayout mFilmDetailCardDetails;

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
    public void setOriginalTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mDetailsOriginalTitle.setVisibility(View.VISIBLE);
            mDetailsOriginalTitle.setContentText(title);
        } else {
            mDetailsOriginalTitle.setVisibility(View.GONE);
        }
    }

    @Override
    public void setGenres(String genres) {
        if (!TextUtils.isEmpty(genres)) {
            mDetailsGenres.setVisibility(View.VISIBLE);
            mDetailsGenres.setContentText(genres);
        } else {
            mDetailsGenres.setVisibility(View.GONE);
        }
    }

    @Override
    public void setYear(String year) {
        if (!TextUtils.isEmpty(year)) {
            mDetailsYear.setVisibility(View.VISIBLE);
            mDetailsYear.setContentText(year);
        } else {
            mDetailsYear.setVisibility(View.GONE);
        }
    }

    @Override
    public void setCountries(String countries) {
        if (!TextUtils.isEmpty(countries)) {
            mDetailsCountries.setVisibility(View.VISIBLE);
            mDetailsCountries.setContentText(countries);
        } else {
            mDetailsCountries.setVisibility(View.GONE);
        }
    }

    @Override
    public void setSummary(String summary) {
        mSummaryTextView.setText(summary);
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

    private void setColorWithPalette(Palette palette) {
        if (palette != null) {
            Palette.Swatch primary = palette.getVibrantSwatch();
            Palette.Swatch secondary = palette.getDarkVibrantSwatch();
            Palette.Swatch tertiary = palette.getLightVibrantSwatch();

            if (primary == null) {
                primary = palette.getMutedSwatch();
            }
            if (secondary == null) {
                secondary = palette.getDarkMutedSwatch();
            }
            if (tertiary == null) {
                tertiary = palette.getLightMutedSwatch();
            }

            if (primary != null && secondary != null && tertiary != null) {

                final ColorScheme scheme = new ColorScheme(
                        primary.getRgb(),
                        secondary.getRgb(),
                        tertiary.getRgb(),
                        primary.getTitleTextColor(),
                        primary.getBodyTextColor());

                setColors(scheme);
            }
        }
    }

    private void setColors(ColorScheme scheme) {
        mTitleTextView.setBackgroundColor(scheme.primaryAccent);
        mTitleTextView.setTextColor(scheme.primaryText);
    }
}
