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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.addict.somefilms.R;

/**
 * Created by CoderHanXin on 2015/05/06.
 */
public class FilmDetailInfoLayout extends LinearLayout {

    private final TextView mTitleTextView;
    private final TextView mContentTextView;

    public FilmDetailInfoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.include_film_detail_info, this, true);

        mTitleTextView = (TextView) findViewById(R.id.textView_title);
        mContentTextView = (TextView) findViewById(R.id.textView_content);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FilmDetailInfoLayout);

        final String title = a.getString(R.styleable.FilmDetailInfoLayout_title);
        if (!TextUtils.isEmpty(title)) {
            mTitleTextView.setText(title);
        }

        a.recycle();
    }

    public TextView getTitleTextView() {
        return mTitleTextView;
    }

    public TextView getContentTextView() {
        return mContentTextView;
    }

    public void setContentText(CharSequence text) {
        mContentTextView.setText(text);
    }
}
