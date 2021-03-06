
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
public class FilmDetail implements Serializable {

    @Expose
    private Rating rating;
    @SerializedName("reviews_count")
    @Expose
    private Number reviewsCount;
    @SerializedName("wish_count")
    @Expose
    private Number wishCount;
    @SerializedName("collect_count")
    @Expose
    private Number collectCount;
    @SerializedName("douban_site")
    @Expose
    private String doubanSite;
    @Expose
    private String year;
    @Expose
    private Images images;
    @Expose
    private String alt;
    @Expose
    private String id;
    @SerializedName("mobile_url")
    @Expose
    private String mobileUrl;
    @Expose
    private String title;
    @SerializedName("do_count")
    @Expose
    private Number doCount;
    @SerializedName("seasons_count")
    @Expose
    private Number seasonsCount;
    @SerializedName("schedule_url")
    @Expose
    private String scheduleUrl;
    @SerializedName("episodes_count")
    @Expose
    private Number episodesCount;
    @Expose
    private List<String> genres = new ArrayList<String>();
    @Expose
    private List<String> countries = new ArrayList<String>();
    @Expose
    private List<Crew> casts = new ArrayList<Crew>();
    @SerializedName("current_season")
    @Expose
    private Number currentSeason;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @Expose
    private String summary;
    @Expose
    private String subtype;
    @Expose
    private List<Crew> directors = new ArrayList<Crew>();
    @SerializedName("comments_count")
    @Expose
    private Number commentsCount;
    @SerializedName("ratings_count")
    @Expose
    private Number ratingsCount;
    @Expose
    private List<String> aka = new ArrayList<String>();

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
     *     The reviewsCount
     */
    public Number getReviewsCount() {
        return reviewsCount;
    }

    /**
     * 
     * @param reviewsCount
     *     The reviews_count
     */
    public void setReviewsCount(Number reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    /**
     * 
     * @return
     *     The wishCount
     */
    public Number getWishCount() {
        return wishCount;
    }

    /**
     * 
     * @param wishCount
     *     The wish_count
     */
    public void setWishCount(Number wishCount) {
        this.wishCount = wishCount;
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
     *     The doubanSite
     */
    public String getDoubanSite() {
        return doubanSite;
    }

    /**
     * 
     * @param doubanSite
     *     The douban_site
     */
    public void setDoubanSite(String doubanSite) {
        this.doubanSite = doubanSite;
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

    /**
     * 
     * @return
     *     The mobileUrl
     */
    public String getMobileUrl() {
        return mobileUrl;
    }

    /**
     * 
     * @param mobileUrl
     *     The mobile_url
     */
    public void setMobileUrl(String mobileUrl) {
        this.mobileUrl = mobileUrl;
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
     *     The doCount
     */
    public Number getDoCount() {
        return doCount;
    }

    /**
     * 
     * @param doCount
     *     The do_count
     */
    public void setDoCount(Number doCount) {
        this.doCount = doCount;
    }

    /**
     * 
     * @return
     *     The seasonsCount
     */
    public Number getSeasonsCount() {
        return seasonsCount;
    }

    /**
     * 
     * @param seasonsCount
     *     The seasons_count
     */
    public void setSeasonsCount(Number seasonsCount) {
        this.seasonsCount = seasonsCount;
    }

    /**
     * 
     * @return
     *     The scheduleUrl
     */
    public String getScheduleUrl() {
        return scheduleUrl;
    }

    /**
     * 
     * @param scheduleUrl
     *     The schedule_url
     */
    public void setScheduleUrl(String scheduleUrl) {
        this.scheduleUrl = scheduleUrl;
    }

    /**
     * 
     * @return
     *     The episodesCount
     */
    public Number getEpisodesCount() {
        return episodesCount;
    }

    /**
     * 
     * @param episodesCount
     *     The episodes_count
     */
    public void setEpisodesCount(Number episodesCount) {
        this.episodesCount = episodesCount;
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
     *     The countries
     */
    public List<String> getCountries() {
        return countries;
    }

    /**
     * 
     * @param countries
     *     The countries
     */
    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    /**
     * 
     * @return
     *     The casts
     */
    public List<Crew> getCasts() {
        return casts;
    }

    /**
     * 
     * @param casts
     *     The casts
     */
    public void setCasts(List<Crew> casts) {
        this.casts = casts;
    }

    /**
     * 
     * @return
     *     The currentSeason
     */
    public Number getCurrentSeason() {
        return currentSeason;
    }

    /**
     * 
     * @param currentSeason
     *     The current_season
     */
    public void setCurrentSeason(Number currentSeason) {
        this.currentSeason = currentSeason;
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
     *     The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 
     * @param summary
     *     The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
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
    public List<Crew> getDirectors() {
        return directors;
    }

    /**
     * 
     * @param directors
     *     The directors
     */
    public void setDirectors(List<Crew> directors) {
        this.directors = directors;
    }

    /**
     * 
     * @return
     *     The commentsCount
     */
    public Number getCommentsCount() {
        return commentsCount;
    }

    /**
     * 
     * @param commentsCount
     *     The comments_count
     */
    public void setCommentsCount(Number commentsCount) {
        this.commentsCount = commentsCount;
    }

    /**
     * 
     * @return
     *     The ratingsCount
     */
    public Number getRatingsCount() {
        return ratingsCount;
    }

    /**
     * 
     * @param ratingsCount
     *     The ratings_count
     */
    public void setRatingsCount(Number ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    /**
     * 
     * @return
     *     The aka
     */
    public List<String> getAka() {
        return aka;
    }

    /**
     * 
     * @param aka
     *     The aka
     */
    public void setAka(List<String> aka) {
        this.aka = aka;
    }

}
