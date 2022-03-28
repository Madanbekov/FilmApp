package kg.geektech.filmapp.ui.films;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import kg.geektech.filmapp.App;
import kg.geektech.filmapp.R;
import kg.geektech.filmapp.databinding.FragmentFilmDetailBinding;
import kg.geektech.filmapp.models.Film;
import retrofit2.Callback;
import retrofit2.Response;


public class FilmDetailFragment extends Fragment {
    private FragmentFilmDetailBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFilmDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String id = getArguments().getString("id");

        App.api.getFilmById(id).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(retrofit2.Call<Film> call, Response<Film> response) {
                if (response.isSuccessful()) {
                    Film film = response.body();
                    assert film != null;
                    binding.producer.setText(film.getProducer());
                    binding.director.setText(film.getDirector());
                    binding.releaseDate.setText(film.getReleaseDate());
                    binding.originalTitle.setText(film.getOriginalTitle());
                    binding.textTittle.setText(film.getTitle());
                    binding.Description.setText(film.getDescription());
                    Glide.with(requireActivity()).load(film.getImage()).into(binding.imageTittle);
            }
            }

            @Override
            public void onFailure(retrofit2.Call<Film> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}