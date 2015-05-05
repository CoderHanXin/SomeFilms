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

package com.addict.somefilms.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.addict.model.entites.Film;
import com.addict.somefilms.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by CoderHanXin on 2015/05/04.
 */
public class FilmsRecyclerAdapter
        extends RecyclerView.Adapter<FilmsRecyclerAdapter.FilmViewHolder> {

    private Context mContext;
    private List<Film> mFilmList;
    private int mWidth;
    private int mHeight;

    public FilmsRecyclerAdapter(List<Film> filmList) {
        mFilmList = filmList;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        // set image size
        DisplayMetrics displaymetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        int margin = mContext.getResources().getDimensionPixelOffset(R.dimen.spacing_tiny);
        mWidth = (screenWidth - margin * 6) / 3;
        mHeight = (int) (mWidth * 1.46);

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film, parent, false);
        return new FilmViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final FilmViewHolder holder, int position) {
        Picasso.with(mContext)
                .load(mFilmList.get(position).getImages().getLarge())
                .resize(mWidth, mHeight)
                .into(holder.filmCoverImageView);
        if (mOnItemClickListener != null) {
            holder.filmCoverImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.filmCoverImageView, position);
                }
            });

            holder.filmCoverImageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.filmCoverImageView, position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mFilmList.size();
    }

    public void appendFilms(List<Film> filmList) {
        mFilmList.addAll(filmList);
        notifyDataSetChanged();
    }

    public List<Film> getFilmList() {
        return mFilmList;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    class FilmViewHolder extends RecyclerView.ViewHolder {
        private ImageView filmCoverImageView;

        public FilmViewHolder(View v) {
            super(v);
            filmCoverImageView = (ImageView) v.findViewById(R.id.imageView_film_cover);
        }

    }
}
