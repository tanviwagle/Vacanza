package com.asterixsolution.ruia.vacanza;

import com.google.gson.annotations.SerializedName;

public class AlbumModel
{
    @SerializedName("packageName")private String packageName;

    public AlbumModel(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

}

