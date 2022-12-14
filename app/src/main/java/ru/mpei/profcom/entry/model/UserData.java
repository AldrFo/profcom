package ru.mpei.profcom.entry.model;

import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("id")
    public int id;
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;

    public UserData(int id, String email, String password){
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
