package com.asterixsolution.ruia.vacanza;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Wagle on 12-01-2018.
 */

public class LoginRegisterModel
{
    @SerializedName("Email")private String Email;
    @SerializedName("FName")private String FName;
    @SerializedName("LName")private  String LName;
    @SerializedName("Pwd")private String Pwd;

    public LoginRegisterModel(String Email, String FName, String LName, String Pwd) {
        this.Email =Email;
        this.FName = FName;
        this.LName = LName;
        this.Pwd = Pwd;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String Pwd) {
        this.Pwd = Pwd;
    }

}
