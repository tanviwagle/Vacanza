package com.asterixsolution.ruia.vacanza.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Wagle on 04-02-2018.
 */

public class HotelModel
{
    @SerializedName("id") String id;
    @SerializedName("name") String name;
    @SerializedName("address") String address;
    @SerializedName("contact") String contact;
    @SerializedName("email") String email;
    @SerializedName("type") String type;
    @SerializedName("cost") String cost;

    public HotelModel(List<HotelModel> body) {
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
