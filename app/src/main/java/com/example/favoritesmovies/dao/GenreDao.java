package com.example.favoritesmovies.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.favoritesmovies.model.Genre;

import java.util.List;

public interface GenreDao {
    @Insert
    void insert(Genre genre);

    @Update
    void update(Genre genre);

    @Delete
    void delete(Genre genre);

    @Query("select * from genres_tables")
    LiveData<List<Genre>> getAllGenres();



}
