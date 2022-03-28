package kg.geektech.filmapp.models;

import com.google.gson.annotations.SerializedName;

public class Film {
    String id;
    String title;
    @SerializedName("original_title")
    String originalTitle;
    String description;
    String image;
    String director;
    String producer;
    @SerializedName("release_date")
    String releaseDate;


    public String getProducer() {
        return producer;
    }

    public String getDirector() {
        return director;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getImage() {
        return image;
    }
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getDescription() {
        return description;
    }
}
