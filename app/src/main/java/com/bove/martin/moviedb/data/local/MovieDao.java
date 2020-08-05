package com.bove.martin.moviedb.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.bove.martin.moviedb.data.model.Movie;

import java.util.List;

/**
 * Created by Martín Bove on 04-Aug-20.
 * E-mail: mbove77@gmail.com
 */
@Dao
public interface MovieDao {
    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> loadPopularMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<Movie> movieList);
}
