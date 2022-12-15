package ru.mpei.profcom.entry.model;

import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("id")
    public int id;
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;
    @SerializedName("learn_group")
    public String group;
    @SerializedName("prof_card_number")
    public String profCard;

    public UserData(int id, String email, String password, String group, String profCard){
        this.id = id;
        this.email = email;
        this.password = password;
        this.group = group;
        this.profCard = profCard;
    }
}
