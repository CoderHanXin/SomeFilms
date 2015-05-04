package com.addict.somefilms.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.addict.model.entites.Film;
import com.addict.somefilms.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by CoderHanXin on 2015/05/04.
 */
public class FilmsRecyclerAdapter extends RecyclerView.Adapter<FilmsRecyclerAdapter.FilmViewHolder> {

    private Context mContext;
    private List<Film> mFilmList;

    public FilmsRecyclerAdapter(List<Film> filmList) {
        mFilmList = filmList;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film, parent, false);
        return new FilmViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        Picasso.with(mContext)
                .load(mFilmList.get(position).getImages().getLarge())
                .into(holder.filmCoverImageView);
    }

    @Override
    public int getItemCount() {
        return mFilmList.size();
    }

    public void appendFilms(List<Film> filmList) {
        mFilmList.addAll(filmList);
        notifyDataSetChanged();
    }

    class FilmViewHolder extends RecyclerView.ViewHolder {
        private ImageView filmCoverImageView;

        public FilmViewHolder(View v) {
            super(v);
            filmCoverImageView = (ImageView) v.findViewById(R.id.imageView_film_cover);
        }

    }
}
