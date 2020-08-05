package com.bove.martin.moviedb.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bove.martin.moviedb.data.MovieRepository;
import com.bove.martin.moviedb.data.model.Movie;
import com.bove.martin.moviedb.data.network.Resource;

import java.util.List;

/**
 * Created by Martín Bove on 04-Aug-20.
 * E-mail: mbove77@gmail.com
 */
public class MovieFragmentViewModel extends ViewModel {

    private final LiveData<Resource<List<Movie>>> popularMovie;
    private MovieRepository movieRepository;
    public MutableLiveData<Integer> numOfCols;

    public MovieFragmentViewModel() {
        movieRepository = new MovieRepository();
        popularMovie = movieRepository.getPopularMovies();
        numOfCols = new MutableLiveData<Integer>(2);
    }

    public LiveData<Resource<List<Movie>>> getPopularMovie() {
        return popularMovie;
    }

    public void setGridView() {
        numOfCols.setValue(2);
    }

    public void setListVIew() {
        numOfCols.setValue(1);
    }
}
