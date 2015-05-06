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

import java.io.Serializable;
import java.util.List;

public class FilmWrapper implements Serializable {
    private Number start;
    private Number count;
    private Number total;
    private List<Film> subjects;

    public FilmWrapper(List<Film> subjects) {
        this.subjects = subjects;
    }

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
