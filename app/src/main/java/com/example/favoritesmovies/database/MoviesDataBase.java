package com.example.favoritesmovies.database;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.favoritesmovies.dao.GenreDao;
import com.example.favoritesmovies.dao.MovieDao;
import com.example.favoritesmovies.model.Genre;
import com.example.favoritesmovies.model.Movie;

@Database(entities = {Genre.class, Movie.class}, version = 1)
public abstract class MoviesDataBase extends RoomDatabase {
    private static MoviesDataBase instance;

    public abstract GenreDao getGenreDao();

    public abstract MovieDao getMovieDao();

    public static synchronized MoviesDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MoviesDataBase.class, "moviesDb")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new InitialDataAsyncTask(instance).execute();
        }
    };

    private static class InitialDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private GenreDao genreDao;
        private MovieDao movieDao;

        public InitialDataAsyncTask(MoviesDataBase dataBase){
            genreDao =dataBase.getGenreDao();
            movieDao = dataBase.getMovieDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {

            Genre comedyGenre = new Genre();
            comedyGenre.setGenreName("Comedy");
            Genre romanceGenre = new Genre();
            romanceGenre.setGenreName("Romance");
            Genre dramaGenre = new Genre();
            dramaGenre.setGenreName("Drama");

            genreDao.insert(comedyGenre);
            genreDao.insert(romanceGenre);
            genreDao.insert(dramaGenre);


            Movie movie1 = new Movie();
            movie1.setMovieName("movie1");
            movie1.setMoviesDescription("description movie 1");
            movie1.setGenreId(1);
            Movie movie2 = new Movie();
            movie2.setMovieName("movie2");
            movie2.setMoviesDescription("description movie 2");
            movie2.setGenreId(2);
            Movie movie3 = new Movie();
            movie3.setMovieName("movie3");
            movie3.setMoviesDescription("description movie 3");
            movie3.setGenreId(3);

            movieDao.insert(movie1);
            movieDao.insert(movie2);
            movieDao.insert(movie3);

            return null;
        }
    }
}
