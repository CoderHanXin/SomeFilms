
package com.addict.model.entites;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Cast {

    @Expose
    private Avatars avatars;
    @Expose
    private String alt;
    @Expose
    private String id;
    @Expose
    private String name;

    /**
     * 
     * @return
     *     The avatars
     */
    public Avatars getAvatars() {
        return avatars;
    }

    /**
     * 
     * @param avatars
     *     The avatars
     */
    public void setAvatars(Avatars avatars) {
        this.avatars = avatars;
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
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

}
