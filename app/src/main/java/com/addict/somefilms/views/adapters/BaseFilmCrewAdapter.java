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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.addict.model.entites.Crew;
import com.addict.somefilms.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by CoderHanXin on 2015/05/07.
 */
public class BaseFilmCrewAdapter extends BaseAdapter {
    private final View.OnClickListener mItemOnClickListener;
    private final Context mContext;
    private final List<Crew> mCrewList;

    BaseFilmCrewAdapter(List<Crew> crewList, Context context, View.OnClickListener itemOnClickListener) {
        mCrewList =crewList;
        mContext = context;
        mItemOnClickListener = itemOnClickListener;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Crew getItem(int position) {
        return mCrewList.get(position);
    }

    @Override
    public int getCount() {
        return mCrewList != null ? mCrewList.size() : 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(getLayoutId(), viewGroup, false);
        }
        final TextView title = (TextView) view.findViewById(R.id.textView_title);
        title.setText(mCrewList.get(position).getName());

        final ImageView imageView = (ImageView) view.findViewById(R.id.imageView_poster);
        Picasso.with(mContext)
                .load(mCrewList.get(position).getAvatars().getMedium())
                .fit()
                .centerCrop()
                .into(imageView);

        view.setOnClickListener(mItemOnClickListener);

        return view;
    }

    protected int getLayoutId() {
        return R.layout.item_detail_card_1line;
    }
}
