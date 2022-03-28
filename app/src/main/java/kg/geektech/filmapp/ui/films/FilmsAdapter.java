package kg.geektech.filmapp.ui.films;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.filmapp.App;
import kg.geektech.filmapp.databinding.ItemFilmsBinding;
import kg.geektech.filmapp.models.Film;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmViewHolder>{

    private List<Film> films = new ArrayList<>();
private ItemClick click;
private ImageView image;

    public FilmsAdapter(ItemClick click) {
        this.click = click;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFilmsBinding binding = ItemFilmsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new FilmViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
holder.onBind(films.get(position));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    protected class FilmViewHolder extends RecyclerView.ViewHolder{
        private ItemFilmsBinding binding;

        public FilmViewHolder(@NonNull ItemFilmsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Film film) {
            binding.tvTitle.setText(film.getTitle());
            binding.tvDescription.setText(film.getDescription());
itemView.setOnClickListener(view -> {
    click.click(film);
});        }
    }
}
