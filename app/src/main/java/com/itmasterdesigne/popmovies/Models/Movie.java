package com.itmasterdesigne.popmovies.Models;

import java.io.Serializable;

/**
 * Created by Aziz on 4/26/19.
 * itmasterdesigne
 * contact@itmasterdesigne.com
 */
public class Movie implements Serializable {
    private String mTitle;
    private String mImageUrl;
    private String mOverView;
    private double mVoteAverage;
    private String mReleaseDate;
    private String mOriginalLanguage;
    private final static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    private final static String IMAGE_SIZE = "w342";

    public Movie(){

    }

    public Movie(String mTitle, String mImageUrl, String mOverView, double mVoteAverage, String mReleaseDate,String OriginalLanguage) {
        this.mTitle = mTitle;
        this.mImageUrl = mImageUrl;
        this.mOverView = mOverView;
        this.mVoteAverage = mVoteAverage;
        this.mReleaseDate = mReleaseDate;
        this.mOriginalLanguage = OriginalLanguage;
    }

    /** ***********************************Setters*********************** **/

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public void setmOverView(String mOverView) {
        this.mOverView = mOverView;
    }

    public void setmVoteAverage(double mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public void setmOriginalLanguage(String originalLanguage) {
        this.mOriginalLanguage = originalLanguage;
    }

    /** ***********************************Getters*********************** **/

    public String getmTitle() {
        return mTitle;
    }

    public String getmImageUrl() {
        return IMAGE_BASE_URL + IMAGE_SIZE + mImageUrl;
    }

    public String getmOverView() {
        return mOverView;
    }

    public double getmVoteAverage() {
        return mVoteAverage;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public String getmOriginalLanguage() {
        return mOriginalLanguage;
    }
}
