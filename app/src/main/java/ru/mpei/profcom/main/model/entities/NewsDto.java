package ru.mpei.profcom.main.model.entities;

import com.google.gson.annotations.SerializedName;

public class NewsDto {

    @SerializedName("id")
    public int id;
    @SerializedName("type")
    public String type;
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("news_date")
    public String date;

    public NewsDto(int id, String type, String title, String description, String date){
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;
        this.date = date;
    }
}
