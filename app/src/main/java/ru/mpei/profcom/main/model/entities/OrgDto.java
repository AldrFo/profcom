package ru.mpei.profcom.main.model.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrgDto implements Serializable {

    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("logo_url")
    public String logoUrl;

    OrgDto(String name, String description, String logoUrl){
        this.name = name;
        this.description = description;
        this.logoUrl = logoUrl;
    }

}
