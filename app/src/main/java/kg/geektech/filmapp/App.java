package kg.geektech.filmapp;

import android.app.Application;

import kg.geektech.filmapp.data.remote.FilmApi;
import kg.geektech.filmapp.data.remote.RetrofitClient;

public class App extends Application {

    private RetrofitClient retrofitClient;
    public static FilmApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        api = retrofitClient.createFilmApi();
    }
}
