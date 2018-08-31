package com.example.android.retrofitdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PojoList {


    @SerializedName("employeeList")
    private ArrayList<PojoDemon> pojoDemons;

    public ArrayList<PojoDemon> getPojoDemons() {
        return pojoDemons;
    }

    public void setPojoDemons(ArrayList<PojoDemon> pojoDemons) {
        this.pojoDemons = pojoDemons;
    }


}

