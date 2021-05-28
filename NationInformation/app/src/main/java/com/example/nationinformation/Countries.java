package com.example.nationinformation;

import android.widget.ArrayAdapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Countries {
    @SerializedName("geonames")
    @Expose
    private ArrayList<Geonames> geonames;

    public ArrayList<Geonames> getGeonames() {
        return geonames;
    }

    public void setGeonames(ArrayList<Geonames> geonames) {
        this.geonames = geonames;
    }
}
