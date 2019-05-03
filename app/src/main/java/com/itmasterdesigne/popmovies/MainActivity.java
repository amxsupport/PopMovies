package com.itmasterdesigne.popmovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.itmasterdesigne.popmovies.Models.Movie;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapetr;
    private TextView mErrorMessageDisplay;
    private ProgressBar mProgressbareIndicator;
    private static final String POPULAR_MOVIE = "popular";
    private static final String TOP_RATED_MOVIE = "top_rated";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView)findViewById(R.id.rv_movie);
        mErrorMessageDisplay = (TextView)findViewById(R.id.tv_error_message_display);

        GridLayoutManager layoutManager = new GridLayoutManager(getBaseContext(),2);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mMovieAdapetr = new MovieAdapter(this);
        mRecyclerView.setAdapter(mMovieAdapetr);

        mProgressbareIndicator = (ProgressBar)findViewById(R.id.pb_loading_indicator);

            loadMovieData(POPULAR_MOVIE);

    }

    @Override
    public void OnClick(Movie movie) {
        Context context = this;
        Class destinationClass = Detail.class;
        Intent intent = new Intent(context, destinationClass);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }

    private void loadMovieData(String SortedBy){
        showMovieDataView();
        new FetchMovieTask().execute(SortedBy);
    }

    private void showMovieDataView(){
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage(){
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    private class FetchMovieTask extends AsyncTask<String,Void, Movie[]>{

        @Override
        protected Movie[] doInBackground(String... strings) {

           String sorted =  strings[0];

            URL movieRequestUrl = OpenMovieJSON.buildUrl(sorted);

            try {
                String jsonMovieResponse = OpenMovieJSON.getResponseFromHttpUrl(movieRequestUrl);

                Movie[] mMovieJsonData = OpenMovieJSON.parceMovieJson(jsonMovieResponse);

                return mMovieJsonData;

            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressbareIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Movie[] movies) {

            mProgressbareIndicator.setVisibility(View.INVISIBLE);
            if(movies != null){
                showMovieDataView();
                mMovieAdapetr.setmListMovie(movies);
            }else {
                showErrorMessage();
            }

        }
    }

/** ////////////////////////////////////// MENU ///////////////////////////////////// **/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.popular) {
            mMovieAdapetr.setmListMovie(null);
            loadMovieData(POPULAR_MOVIE);
            setTitle(R.string.action_popular);

            return true;
        }
        if (id == R.id.topRated){
            mMovieAdapetr.setmListMovie(null);
            loadMovieData(TOP_RATED_MOVIE);
            setTitle(R.string.action_topRated);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** ////////////////////////////////////// END MENU ///////////////////////////////////// **/
}
