package com.addict.model.entites;

import java.util.List;

/**
 * Created by CoderHanXin on 2015/05/04.
 */
public class FilmWrapper {
    private Number start;
    private Number count;
    private Number total;
    private List<Film> subjects;

    public Number getStart() {
        return start;
    }

    public void setStart(Number start) {
        this.start = start;
    }

    public Number getCount() {
        return count;
    }

    public void setCount(Number count) {
        this.count = count;
    }

    public Number getTotal() {
        return total;
    }

    public void setTotal(Number total) {
        this.total = total;
    }

    public List<Film> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Film> subjects) {
        this.subjects = subjects;
    }
}
