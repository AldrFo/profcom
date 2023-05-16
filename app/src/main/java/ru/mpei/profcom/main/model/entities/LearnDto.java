package ru.mpei.profcom.main.model.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LearnDto implements Serializable {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;

    public LearnDto(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
