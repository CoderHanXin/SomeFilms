
package com.addict.model.entites;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Rating {

    @Expose
    private Integer max;
    @Expose
    private Double average;
    @Expose
    private String stars;
    @Expose
    private Integer min;

    /**
     * 
     * @return
     *     The max
     */
    public Integer getMax() {
        return max;
    }

    /**
     * 
     * @param max
     *     The max
     */
    public void setMax(Integer max) {
        this.max = max;
    }

    /**
     * 
     * @return
     *     The average
     */
    public Double getAverage() {
        return average;
    }

    /**
     * 
     * @param average
     *     The average
     */
    public void setAverage(Double average) {
        this.average = average;
    }

    /**
     * 
     * @return
     *     The stars
     */
    public String getStars() {
        return stars;
    }

    /**
     * 
     * @param stars
     *     The stars
     */
    public void setStars(String stars) {
        this.stars = stars;
    }

    /**
     * 
     * @return
     *     The min
     */
    public Integer getMin() {
        return min;
    }

    /**
     * 
     * @param min
     *     The min
     */
    public void setMin(Integer min) {
        this.min = min;
    }

}
