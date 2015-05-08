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
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.addict.model.entites.Crew;
import com.addict.model.entites.Film;
import com.addict.somefilms.R;
import com.addict.somefilms.model.ColorScheme;
import com.addict.somefilms.mvp.presenters.FilmDetailPresenter;
import com.addict.somefilms.mvp.views.FilmDetailView;
import com.addict.somefilms.views.adapters.BaseFilmCrewAdapter;
import com.addict.somefilms.views.customs.FilmDetailCardLayout;
import com.addict.somefilms.views.customs.FilmDetailInfoLayout;
import com.addict.somefilms.views.customs.ViewRecycler;
import com.google.common.base.Joiner;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class FilmDetailActivity extends Activity
        implements FilmDetailView {

    @InjectView(R.id.cover_imageView)
    ImageView mCoverImageView;
    @InjectView(R.id.title_textView)
    TextView mTitleTextView;
    @InjectView(R.id.summary_textView)
    TextView mSummaryTextView;
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

    @InjectView(R.id.film_detail_details_card)
    FilmDetailCardLayout mFilmDetailDetailsCard;
    @InjectView(R.id.film_detail_casts_card)
    FilmDetailCardLayout mFilmDetailCastsCard;
    @InjectView(R.id.film_detail_directors_card)
    FilmDetailCardLayout mFilmDetailDirectorsCard;
    @InjectView(R.id.rating_layout)
    RelativeLayout mRatingLayout;

    private FilmDetailPresenter mFilmDetailPresenter;
    private Film mFilm;
    private LayerDrawable mRatingBarLayer;
    private int mWidth;
    private int mHeight;

    private FilmCastAdapter mFilmCastAdapter;
    private FilmDirectorAdapter mFilmDirectorAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        ButterKnife.inject(this);

        mRatingBarLayer = (LayerDrawable) mRatingBar.getProgressDrawable();

        setImageSize();

        mFilm = (Film) getIntent().getSerializableExtra(FilmListActivity.EXTRA_FILM);

        bindView();

        mFilmDetailPresenter = new FilmDetailPresenter(this, mFilm.getId());
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
    public void setGenres(List<String> genres) {
        Joiner joiner = Joiner.on("/").skipNulls();
        String joinedGenres = joiner.join(genres);
        if (!TextUtils.isEmpty(joinedGenres)) {
            mDetailsGenres.setVisibility(View.VISIBLE);
            mDetailsGenres.setContentText(joinedGenres);
        } else {
            mDetailsGenres.setVisibility(View.GONE);
        }
    }

    @Override
    public void setCountries(List<String> countries) {
        Joiner joiner = Joiner.on("/").skipNulls();
        String joinedCountries = joiner.join(countries);
        if (!TextUtils.isEmpty(joinedCountries)) {
            mDetailsCountries.setVisibility(View.VISIBLE);
            mDetailsCountries.setContentText(joinedCountries);
        } else {
            mDetailsCountries.setVisibility(View.GONE);
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
    public void setSummary(String summary) {
        mSummaryTextView.setText(summary);
    }

    @Override
    public void setRating(Float rating) {
        mRatingBar.setRating(rating/2);
        mRatingTextView.setText(String.valueOf(rating));
    }

    @Override
    public void setCasts(List<Crew> crewList) {
        mFilmCastAdapter = new FilmCastAdapter(crewList, this);

        final View.OnClickListener seeMoreListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FilmDetailActivity.this, "see more click", Toast.LENGTH_LONG).show();
            }
        };

        final ViewGroup layout = (ViewGroup) mFilmDetailCastsCard.findViewById(R.id.card_content);

        final ViewRecycler viewRecycler = new ViewRecycler(layout);
        viewRecycler.recycledViews();
        if (!mFilmCastAdapter.isEmpty()) {

            final int maxItems = getResources().getInteger(R.integer.detail_card_max_items);
            final int adapterCount = mFilmCastAdapter.getCount();

            for (int i = 0; i < Math.min(maxItems, adapterCount); i++) {
                View view = mFilmCastAdapter.getView(i, viewRecycler.getRecycledView(), layout);
                layout.addView(view);
            }

            final boolean showSeeMore = maxItems < mFilmCastAdapter.getCount();
            mFilmDetailCastsCard.setSeeMoreVisibility(showSeeMore);
            mFilmDetailCastsCard.setSeeMoreOnClickListener(showSeeMore ? seeMoreListener : null);
        }
        viewRecycler.clearRecycledViews();
    }

    @Override
    public void setDirectors(List<Crew> crewList) {
        mFilmDirectorAdapter = new FilmDirectorAdapter(crewList, this);

        final View.OnClickListener seeMoreListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FilmDetailActivity.this, "see more click", Toast.LENGTH_SHORT).show();
            }
        };

        final ViewGroup layout = (ViewGroup) mFilmDetailDirectorsCard.findViewById(R.id.directors_card_content);

        final ViewRecycler viewRecycler = new ViewRecycler(layout);
        viewRecycler.recycledViews();
        if (!mFilmDirectorAdapter.isEmpty()) {

            final int maxItems = getResources().getInteger(R.integer.detail_card_max_items);
            final int adapterCount = mFilmDirectorAdapter.getCount();

            for (int i = 0; i < Math.min(maxItems, adapterCount); i++) {
                View view = mFilmDirectorAdapter.getView(i, viewRecycler.getRecycledView(), layout);
                layout.addView(view);
            }

            final boolean showSeeMore = maxItems < mFilmDirectorAdapter.getCount();
            mFilmDetailDirectorsCard.setSeeMoreVisibility(showSeeMore);
            mFilmDetailDirectorsCard.setSeeMoreOnClickListener(showSeeMore ? seeMoreListener : null);
        }
        viewRecycler.clearRecycledViews();
    }

    private void setImageSize() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        mWidth = displaymetrics.widthPixels;
        mHeight = height / 3;
        mCoverImageView.setLayoutParams(new RelativeLayout.LayoutParams(mWidth, mHeight));
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

    private void bindView() {
        setFilmImage(mFilm.getImages().getLarge());
        setTitle(mFilm.getTitle());
        setRating(mFilm.getRating().getAverage());
        setOriginalTitle(mFilm.getOriginalTitle());
        setGenres(mFilm.getGenres());
        setYear(mFilm.getYear());
        setCasts(mFilm.getCasts());
        setDirectors(mFilm.getDirectors());
    }

    private void setColors(ColorScheme scheme) {
        mTitleTextView.setBackgroundColor(scheme.primaryAccent);
        mTitleTextView.setTextColor(scheme.primaryText);
        mRatingLayout.setBackgroundColor(scheme.primaryAccent);
        mRatingBarLayer.getDrawable(2).setColorFilter(scheme.primaryText, PorterDuff.Mode.SRC_ATOP);
        mRatingBarLayer.getDrawable(0).setColorFilter(scheme.secondaryText, PorterDuff.Mode.SRC_ATOP);
        mRatingBarLayer.getDrawable(0).setColorFilter(scheme.secondaryText, PorterDuff.Mode.SRC_ATOP);
        mRatingTextView.setTextColor(scheme.secondaryText);
    }

    private class FilmCastAdapter extends BaseFilmCrewAdapter {
        FilmCastAdapter(List<Crew> crewList, Context context) {
            super(crewList, context, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(FilmDetailActivity.this, "cast item click", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class FilmDirectorAdapter extends BaseFilmCrewAdapter {
        FilmDirectorAdapter(List<Crew> crewList, Context context) {
            super(crewList, context, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(FilmDetailActivity.this, "director item click", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
