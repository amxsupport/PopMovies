package com.itmasterdesigne.popmovies;

import android.net.Uri;

import android.util.Log;

import com.itmasterdesigne.popmovies.Models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Aziz on 4/27/19.
 * itmasterdesigne
 * contact@itmasterdesigne.com
 */
public class OpenMovieJSON {

    private static final String BASE_MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/";

    // you'll find the api key in themoviedb.org
    /** TODO you should set your own API_KEY **/
    private static final String API_KEY = "your own api key";

    public static Movie[] parceMovieJson(String json) throws JSONException {

        /**
         {
         "page": 1,
         "total_results": 19811,
         "total_pages": 991,
         "results": [
         {
         "vote_count": 1541,
         "id": 299534,
         "video": false,
         "vote_average": 8.8,
         "title": "Avengers: Endgame",
         "popularity": 514.515,
         "poster_path": "\/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
         "original_language": "en",
         "original_title": "Avengers: Endgame",
         "genre_ids": [
         12,
         878,
         28
         ],
         "backdrop_path": "\/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg",
         "adult": false,
         "overview": "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
         "release_date": "2019-04-24"
         }
         **/

        Movie[] mMovieData ;

        final String RESULTS_POP_MOVIE = "results";
        final String TITLE = "title";
        final String IMAGE_URL = "poster_path";
        final String OVERVIEW = "overview";
        final String VOTE_AVERAGE ="vote_average";
        final String RELEASE_DATE ="release_date";
        final String OWM_MESSAGE_CODE = "cod";
        final String ORIGINAL_LANGUAGE = "original_language";

        JSONObject movieJsonObjet = new JSONObject(json);

        if (movieJsonObjet.has(OWM_MESSAGE_CODE)) {
            int errorCode = movieJsonObjet.getInt(OWM_MESSAGE_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    /* Location invalid */
                    return null;
                default:
                    /* Server probably down */
                    return null;
            }
        }

        JSONArray movieJsonArray = movieJsonObjet.getJSONArray(RESULTS_POP_MOVIE);

        Movie mMovie;
        mMovieData = new Movie[movieJsonArray.length()];

        for (int i=0; i< movieJsonArray.length();i++){

            JSONObject popMovie = movieJsonArray.getJSONObject(i);
            mMovie = new Movie();
            mMovie.setmTitle(popMovie.optString(TITLE));
            mMovie.setmImageUrl(popMovie.optString(IMAGE_URL));
            mMovie.setmOverView(popMovie.optString(OVERVIEW));
            mMovie.setmReleaseDate(popMovie.optString(RELEASE_DATE));
            mMovie.setmVoteAverage(popMovie.getDouble(VOTE_AVERAGE));
            mMovie.setmOriginalLanguage(popMovie.optString(ORIGINAL_LANGUAGE));


            mMovieData[i]= mMovie;

        }

        return mMovieData;
    }

    public static URL buildUrl(String SortBy){

        Uri buildUri = Uri.parse(BASE_MOVIE_BASE_URL + SortBy + "?api_key=" + API_KEY);

        buildUri.buildUpon().build();

        URL url = null;

        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        Log.v("dollar","url = " + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
