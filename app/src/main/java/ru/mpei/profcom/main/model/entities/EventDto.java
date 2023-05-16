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
    public boolean canRegister;
    public boolean alreadyGoing = false;

    public EventDto(int id, String name, String description, String link){
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
        this.canRegister = false;
    }

    public EventDto(int id, String name, String description, String link, boolean canRegister){
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
        this.canRegister = canRegister;
    }

    public EventDto(int id, String name, String description, String link, boolean canRegister, boolean alreadyGoing){
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
        this.canRegister = canRegister;
        this.alreadyGoing = alreadyGoing;
    }
}
