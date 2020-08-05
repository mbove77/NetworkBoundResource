package com.bove.martin.moviedb.data.model;

import java.util.List;

/**
 * Created by Martín Bove on 03-Aug-20.
 * E-mail: mbove77@gmail.com
 */
public class MovieResponse {
    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
