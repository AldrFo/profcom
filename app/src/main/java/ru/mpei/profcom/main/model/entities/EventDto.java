package ru.mpei.profcom.main.model.entities;

import com.google.gson.annotations.SerializedName;

public class EventDto {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("link")
    public String link;

    public EventDto(int id, String name, String description, String link){
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
    }
}
