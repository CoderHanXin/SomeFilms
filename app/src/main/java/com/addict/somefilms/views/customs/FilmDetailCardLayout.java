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

package com.addict.somefilms.views.customs;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.addict.somefilms.R;

/**
 * Created by CoderHanXin on 2015/05/06.
 */
public class FilmDetailCardLayout extends FrameLayout {
    private final View mTitleLayout;
    private final TextView mTitleTextView;
    private final TextView mSeeMoreTextView;
    private LinearLayout mCardContent;

    public FilmDetailCardLayout(Context context) {
        this(context, null);
    }

    public FilmDetailCardLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilmDetailCardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.include_film_detail_card, this, true);

        mTitleLayout = findViewById(R.id.card_header);
        mTitleTextView = (TextView) findViewById(R.id.textView_title);
        mSeeMoreTextView = (TextView) findViewById(R.id.textView_see_more);
        mCardContent = (LinearLayout) findViewById(R.id.card_content_holder);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FilmDetailCardLayout);
        final String title = a.getString(R.styleable.FilmDetailCardLayout_title);
        if (!TextUtils.isEmpty(title)) {
            mTitleTextView.setText(title);
        }
        a.recycle();
    }

    public void setSeeMoreVisibility(boolean visible) {
        mSeeMoreTextView.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setSeeMoreOnClickListener(OnClickListener listener) {
        mTitleLayout.setOnClickListener(listener);
    }

    public void setTitle(CharSequence title) {
        mTitleTextView.setText(title);
    }

    public void setTitle(int titleResId) {
        setTitle(getResources().getString(titleResId));
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (mCardContent != null) {
            mCardContent.addView(child, index, params);
        } else {
            super.addView(child, index, params);
        }
    }


}
