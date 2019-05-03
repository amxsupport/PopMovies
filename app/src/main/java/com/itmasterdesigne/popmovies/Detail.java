package com.itmasterdesigne.popmovies;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itmasterdesigne.popmovies.Models.Movie;
import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity {

    private TextView mTitle;
    private TextView mDateRelease;
    private TextView mAverageRating;
    private TextView mLanguage;
    private TextView mOverview;
    private ImageView mPosterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTitle = (TextView)findViewById(R.id.movie_title_tv);
        mDateRelease = (TextView)findViewById(R.id.date_release_tv);
        mAverageRating = (TextView)findViewById(R.id.vote_average_tv);
        mLanguage = (TextView)findViewById(R.id.original_language_tv);
        mOverview = (TextView)findViewById(R.id.overview_tv);
        mPosterImageView = (ImageView)findViewById(R.id.movie_poster_iv);

        Intent intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra("movie");

        mTitle.setText(movie.getmTitle());
        mDateRelease.setText(movie.getmReleaseDate());
        mOverview.setText(movie.getmOverView());
        mLanguage.setText("Language : " + movie.getmOriginalLanguage());
        mAverageRating.setText(String.valueOf(movie.getmVoteAverage()) + "/10");

        Picasso.get().load(movie.getmImageUrl()).error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher).into(mPosterImageView);

    }

}
