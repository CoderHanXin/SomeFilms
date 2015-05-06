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

package com.addict.somefilms.mvp.views;

import com.addict.model.entites.Film;

import java.util.List;

/**
 * Created by CoderHanXin on 2015/05/04.
 */
public interface FilmsView extends MvpView {
    void showFilms(List<Film> filmList);

    void appendFilms(List<Film> filmList);

    void showLoading();

    void hideLoading();

    void showLoadingLabel();

    void hideLoadingLabel();

    boolean isTheListEmpty();
}
