package com.itmasterdesigne.popmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.itmasterdesigne.popmovies.Models.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by Aziz on 4/27/19.
 * itmasterdesigne
 * contact@itmasterdesigne.com
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    private Movie[] mListMovie;

    private final MovieAdapterOnClickHandler handler;

    public MovieAdapter(MovieAdapterOnClickHandler handler) {
        this.handler = handler;
    }

    public interface MovieAdapterOnClickHandler {
        void OnClick(Movie movie);
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView mMovieImageView;

        public MovieAdapterViewHolder(View itemView) {
            super(itemView);
            this.mMovieImageView = (ImageView)itemView.findViewById(R.id.iv_movie);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Movie ClickedMovie = mListMovie[adapterPosition];
            handler.OnClick(ClickedMovie);
        }
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_movie,viewGroup,false);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder movieAdapterViewHolder, int position) {

        Movie popMovie = mListMovie[position];

        Picasso.get().load(popMovie.getmImageUrl()).error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher).into(movieAdapterViewHolder.mMovieImageView);
        Picasso.get().setLoggingEnabled(true);

    }

    @Override
    public int getItemCount() {
        if(null == mListMovie) return 0;
        return mListMovie.length;
    }


    public void setmListMovie(Movie[] ListMovie) {
        this.mListMovie = ListMovie;
        notifyDataSetChanged();
    }
}
