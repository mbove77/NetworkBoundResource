package com.bove.martin.moviedb.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.bove.martin.moviedb.data.model.Movie;

/**
 * Created by Martín Bove on 04-Aug-20.
 * E-mail: mbove77@gmail.com
 */
@Database(entities = {Movie.class}, version = 2, exportSchema = false)
public abstract class MovieRomDataBase extends RoomDatabase {
    public abstract MovieDao getMovieDao();
}
