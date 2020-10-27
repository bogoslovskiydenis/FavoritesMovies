package com.example.favoritesmovies.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.favoritesmovies.dao.GenreDao;
import com.example.favoritesmovies.dao.MovieDao;
import com.example.favoritesmovies.database.MoviesDataBase;
import com.example.favoritesmovies.model.Genre;
import com.example.favoritesmovies.model.Movie;

import java.util.List;

public class AppRepository {
    private GenreDao genreDao;
    private MovieDao movieDao;

    private LiveData<List<Genre>> genres;
    private LiveData<List<Movie>> movies;

    public AppRepository(Application application){
        MoviesDataBase dataBase = MoviesDataBase.getInstance(application);
        genreDao = dataBase.getGenreDao();
        movieDao = dataBase.getMovieDao();
    }

    public LiveData<List<Genre>> getGenres(){
        return genreDao.getAllGenres();
    }

    //Метод чтобы получить фильмы
    public LiveData<List<Movie>> getGenreMovies(int genreId)
    {
        return movieDao.getGenreMovies(genreId);
    }

    //insertGenre
    public void insertGenre(Genre genre){
        new InsertGenreAsyncTask(genreDao).execute(genre);
    }

    //insertMovie
    public void insertMovie(Movie movie){
        new InsertMoviesAsyncTask(movieDao).execute(movie);
    }

    //Async ->> Genre
    private static class InsertGenreAsyncTask extends AsyncTask<Genre,Void, Void>{
        private GenreDao genreDao;

        public InsertGenreAsyncTask(GenreDao genreDao) {
            this.genreDao = genreDao;
        }

        @Override
        protected Void doInBackground(Genre... genres) {
            genreDao.insert(genres[0]);
            return null;
        }
    }
    //Async ->> Movie
    private  static class InsertMoviesAsyncTask extends AsyncTask<Movie, Void  , Void>{
        private MovieDao movieDao;

        public InsertMoviesAsyncTask(MovieDao movieDao){
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.insert(movies[0]);
            return null;
        }
    }


    //update
    public void updateGenre(Genre genre){
        new UpdateGenreAsyncTask(genreDao).execute(genre);
    }

    //update
    public void updateMovie(Movie movie){
        new UpdateMoviesAsyncTask(movieDao).execute(movie);
    }

    //Async -> Genre
    private static class UpdateGenreAsyncTask extends AsyncTask<Genre,Void, Void>{
        private GenreDao genreDao;

        public UpdateGenreAsyncTask(GenreDao genreDao) {
            this.genreDao = genreDao;
        }

        @Override
        protected Void doInBackground(Genre... genres) {
            genreDao.update(genres[0]);
            return null;
        }
    }
    //Async -> Movie
    private  static class UpdateMoviesAsyncTask extends AsyncTask<Movie, Void  , Void>{
        private MovieDao movieDao;

        public UpdateMoviesAsyncTask(MovieDao movieDao){
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.update(movies[0]);
            return null;
        }
    }

    //delete
    public void deleteGenre(Genre genre){
        new DeleteGenreAsyncTask(genreDao).execute(genre);
    }

    //delete
    public void deleteMovie(Movie movie){
        new DeleteMoviesAsyncTask(movieDao).execute(movie);
    }

    //Async -> Genre
    private static class DeleteGenreAsyncTask extends AsyncTask<Genre,Void, Void>{
        private GenreDao genreDao;

        public DeleteGenreAsyncTask(GenreDao genreDao) {
            this.genreDao = genreDao;
        }

        @Override
        protected Void doInBackground(Genre... genres) {
            genreDao.delete(genres[0]);
            return null;
        }
    }
    //Async -> Movie
    private  static class DeleteMoviesAsyncTask extends AsyncTask<Movie, Void  , Void>{
        private MovieDao movieDao;

        public DeleteMoviesAsyncTask(MovieDao movieDao){
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.delete(movies[0]);
            return null;
        }
    }
}
