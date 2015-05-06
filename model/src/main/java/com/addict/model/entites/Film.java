
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

package com.addict.model.entites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Film implements Serializable{

    @Expose
    private Rating rating;
    @Expose
    private List<String> genres = new ArrayList<String>();
    @SerializedName("collect_count")
    @Expose
    private Number collectCount;
    @Expose
    private List<Cast> casts = new ArrayList<Cast>();
    @Expose
    private String title;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @Expose
    private String subtype;
    @Expose
    private List<Director> directors = new ArrayList<Director>();
    @Expose
    private String year;
    @Expose
    private Images images;
    @Expose
    private String alt;
    @Expose
    private String id;

    /**
     *
     * @return
     *     The rating
     */
    public Rating getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     *     The rating
     */
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     *     The genres
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     *
     * @param genres
     *     The genres
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     *
     * @return
     *     The collectCount
     */
    public Number getCollectCount() {
        return collectCount;
    }

    /**
     *
     * @param collectCount
     *     The collect_count
     */
    public void setCollectCount(Number collectCount) {
        this.collectCount = collectCount;
    }

    /**
     *
     * @return
     *     The casts
     */
    public List<Cast> getCasts() {
        return casts;
    }

    /**
     *
     * @param casts
     *     The casts
     */
    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    /**
     *
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     *     The originalTitle
     */
    public String getOriginalTitle() {
        return originalTitle;
    }

    /**
     *
     * @param originalTitle
     *     The original_title
     */
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    /**
     *
     * @return
     *     The subtype
     */
    public String getSubtype() {
        return subtype;
    }

    /**
     *
     * @param subtype
     *     The subtype
     */
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    /**
     *
     * @return
     *     The directors
     */
    public List<Director> getDirectors() {
        return directors;
    }

    /**
     *
     * @param directors
     *     The directors
     */
    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    /**
     *
     * @return
     *     The year
     */
    public String getYear() {
        return year;
    }

    /**
     *
     * @param year
     *     The year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     *
     * @return
     *     The images
     */
    public Images getImages() {
        return images;
    }

    /**
     *
     * @param images
     *     The images
     */
    public void setImages(Images images) {
        this.images = images;
    }

    /**
     *
     * @return
     *     The alt
     */
    public String getAlt() {
        return alt;
    }

    /**
     *
     * @param alt
     *     The alt
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    /**
     *
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

}
