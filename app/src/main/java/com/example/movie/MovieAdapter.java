package com.example.movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies != null ? movies : new ArrayList<>();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.title.setText(movie.getTitle());
        holder.year.setText(
                movie.getYear() != null ?
                        String.valueOf(movie.getYear()) :
                        holder.itemView.getContext().getString(R.string.default_year)
        );
        holder.genre.setText(
                !movie.getGenre().equals("N/A") ?
                        movie.getGenre() :
                        holder.itemView.getContext().getString(R.string.default_genre)
        );

        int posterResId = holder.itemView.getResources()
                .getIdentifier(
                        movie.getPosterResource(),
                        "drawable",
                        holder.itemView.getContext().getPackageName()
                );

        if (posterResId != 0) {
            holder.poster.setImageResource(posterResId);
        } else {
            holder.poster.setImageResource(R.drawable.default_poster);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, year, genre;
        ImageView poster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            year = itemView.findViewById(R.id.year);
            genre = itemView.findViewById(R.id.genre);
            poster = itemView.findViewById(R.id.poster);
        }
    }
}