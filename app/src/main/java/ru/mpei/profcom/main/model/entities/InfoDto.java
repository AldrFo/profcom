package ru.mpei.profcom.main.model.entities;

import com.google.gson.annotations.SerializedName;

public final class InfoDto {
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("image_url")
    public String imageUrl;

    InfoDto(String title, String description, String imageUrl){
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}
