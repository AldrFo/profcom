package ru.mpei.profcom.main.model.entities;

import com.google.gson.annotations.SerializedName;

public class TaskDto {

    @SerializedName("id")
    public int id;
    @SerializedName("pb_member_id")
    public int pbMemberId;
    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("start")
    public String start;
    @SerializedName("deadline")
    public String deadline;

    public TaskDto(int id, String name, String description, String start, String deadline){
        this.pbMemberId = id;
        this.name = name;
        this.description = description;
        this.start = start;
        this.deadline = deadline;
    }
}
