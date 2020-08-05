package com.bove.martin.moviedb.data.remote;

import com.bove.martin.moviedb.data.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Martín Bove on 03-Aug-20.
 * E-mail: mbove77@gmail.com
 */
public interface MovieApiService {
    @GET("movie/popular")
    Call<MovieResponse> loadPopularMovies();
}
