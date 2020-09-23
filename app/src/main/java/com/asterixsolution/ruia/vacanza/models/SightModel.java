package com.asterixsolution.ruia.vacanza.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Wagle on 01-02-2018.
 */

public class SightModel
{
    @SerializedName("name") private String sightName;

    public SightModel(String sightName)
    {
        this.sightName=sightName;
    }

    public String getSightName() {
        return sightName;
    }

    public void setSightName(String sightName) {
        this.sightName = sightName;
    }
}

