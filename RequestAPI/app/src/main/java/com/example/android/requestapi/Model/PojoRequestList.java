package com.example.android.requestapi.Model;

import java.util.ArrayList;
import java.util.List;

public class PojoRequestList {

    ArrayList<PojoRequest> data;

    public PojoRequestList(ArrayList<PojoRequest> data) {
        this.data = data;
    }


    public ArrayList<PojoRequest> getData() {
        return data;
    }

    public void setData(ArrayList<PojoRequest> data) {
        this.data = data;
    }
}
