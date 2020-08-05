package com.bove.martin.moviedb.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.bove.martin.moviedb.MyApp;
import com.bove.martin.moviedb.commons.Constantes;
import com.bove.martin.moviedb.data.local.MovieDao;
import com.bove.martin.moviedb.data.local.MovieRomDataBase;
import com.bove.martin.moviedb.data.model.Movie;
import com.bove.martin.moviedb.data.model.MovieResponse;
import com.bove.martin.moviedb.data.network.NetworkBoundResource;
import com.bove.martin.moviedb.data.network.Resource;
import com.bove.martin.moviedb.data.remote.MovieApiService;
import com.bove.martin.moviedb.data.remote.RequestInterceptor;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Martín Bove on 03-Aug-20.
 * E-mail: mbove77@gmail.com
 */
public class MovieRepository {
    private final MovieApiService movieApiService;
    private final MovieDao movieDao;

    public MovieRepository() {
        // local
        MovieRomDataBase movieRomDataBase = Room.databaseBuilder(MyApp.getContext(), MovieRomDataBase.class, "db_movies")
                .fallbackToDestructiveMigration()
                .build();
        movieDao = movieRomDataBase.getMovieDao();

        // remoto
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.addInterceptor(new RequestInterceptor());
        OkHttpClient cliente = okBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_BASE_URL)
                .client(cliente)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieApiService = retrofit.create(MovieApiService.class);
    }

    public LiveData<Resource<List<Movie>>> getPopularMovies() {
        // El primer parametro es lo que devuelve room el segundo retrofit
        return new NetworkBoundResource<List<Movie>, MovieResponse>(){

            @Override
            protected void saveCallResult(@NonNull MovieResponse item) {
                movieDao.saveMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<Movie>> loadFromDb() {
                return movieDao.loadPopularMovies();
            }

            @NonNull
            @Override
            protected Call<MovieResponse> createCall() {
                return movieApiService.loadPopularMovies();
            }
        }.getAsLiveData();
    }


}
