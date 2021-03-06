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

import com.addict.model.entites.Crew;

import java.util.List;

/**
 * Created by CoderHanXin on 2015/04/29.
 */
public interface FilmDetailView extends MvpView {

    void setTitle(String title);

    void setOriginalTitle(String title);

    void setGenres(List<String> genres);

    void setYear(String year);

    void setCountries(List<String> countries);

    void setRating(Float rating);

    void setFilmImage(String url);

    void setSummary(String summary);

    void setCasts(List<Crew> crewList);

    void setDirectors(List<Crew> crewList);
}
