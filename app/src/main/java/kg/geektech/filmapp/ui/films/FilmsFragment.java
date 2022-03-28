package kg.geektech.filmapp.ui.films;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kg.geektech.filmapp.App;
import kg.geektech.filmapp.R;
import kg.geektech.filmapp.databinding.FragmentFilmsBinding;
import kg.geektech.filmapp.models.Film;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FilmsFragment extends Fragment implements ItemClick {
    private FragmentFilmsBinding binding;
    private FilmsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter= new FilmsAdapter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentFilmsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recycler.setAdapter(adapter);
        App.api.getFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if (response.isSuccessful()&& response.body() !=null){
                    adapter.setFilms(response.body());
                }else{
                    Log.e("TAG","onFailure" + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                Log.e("TAG","onFailure" + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void click(Film film) {
        Bundle bundle = new Bundle();
        bundle.putString("id",film.getId());
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.filmDetailFragment, bundle);
    }
}