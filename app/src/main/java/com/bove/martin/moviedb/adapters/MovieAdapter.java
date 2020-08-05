package com.bove.martin.moviedb.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bove.martin.moviedb.R;
import com.bove.martin.moviedb.commons.Constantes;
import com.bove.martin.moviedb.data.model.Movie;
import com.bumptech.glide.Glide;

import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movieList;
    private Context context;

    public MovieAdapter(Context context, List<Movie> items) {
        this.movieList = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.movie = movieList.get(position);

        Glide.with(context)
                .load(Constantes.COVER_FOTO_BASE_URL + holder.movie.getPosterPath())
                .into(holder.imageViewCover);
    }

    public void setData(List<Movie> movies) {
        this.movieList = movies;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(movieList != null) {
            return movieList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imageViewCover;

        public Movie movie;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageViewCover = (ImageView) view.findViewById(R.id.imageViewCover);

        }

    }
}