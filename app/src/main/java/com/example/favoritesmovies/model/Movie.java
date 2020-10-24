package com.example.favoritesmovies.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies_table",
        foreignKeys = @ForeignKey(entity = Genre.class, parentColumns = "id", childColumns = "genre_id", onDelete = ForeignKey.CASCADE))

public class Movie extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movie_id")
    private int movieId;

    @ColumnInfo(name = "movie_name")
    private String movieName;

    @ColumnInfo(name = "movie_description")
    private String moviesDescription;

    @ColumnInfo(name = "genre_id")
    private int genreId;


    public Movie() {

    }

    @Bindable
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
        notifyPropertyChanged(BR.movieId);
    }

    @Bindable
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
        notifyPropertyChanged(BR.movieName);
    }

    @Bindable
    public String getMoviesDescription() {
        return moviesDescription;
    }

    public void setMoviesDescription(String moviesDescription) {
        this.moviesDescription = moviesDescription;
        notifyPropertyChanged(BR.moviesDescription);
    }

    @Bindable
    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
        notifyPropertyChanged(BR.genreId);
    }


    public Movie(int movieId, String movieName, String moviesDescription, int genreId) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.moviesDescription = moviesDescription;
        this.genreId = genreId;
    }
}
