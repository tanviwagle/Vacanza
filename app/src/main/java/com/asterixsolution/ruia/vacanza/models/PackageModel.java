package com.asterixsolution.ruia.vacanza.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PackageModel
{
    @SerializedName("id")private String id;
    @SerializedName("packageName")private String packageName;
    @SerializedName("cost")private String cost;
    @SerializedName("placeid")private String placeid;
    @SerializedName("imageUrl") private String imageUrl;
    @SerializedName("duration") private String duration;

    public ArrayList<SightModel> getSight() {
        return sight;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSight(ArrayList<SightModel> sight) {
        this.sight = sight;
    }

    @SerializedName("sights")ArrayList<SightModel> sight;

    public PackageModel(String packageName, String imageUrl) {
        this.packageName = packageName;
        this.imageUrl=imageUrl;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getPlaceid() {
        return placeid;
    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }


    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}

