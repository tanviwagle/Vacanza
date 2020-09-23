package com.asterixsolution.ruia.vacanza.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Wagle on 04-02-2018.
 */

public class PlaceModel
{
    @SerializedName("name") private String name;
    @SerializedName("id") private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
