package com.example.nationinformation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geonames {
    @SerializedName("countryName")
    @Expose
    private String countryName;

    @SerializedName("areaInSqKm")
    @Expose
    private String areaInSqKm;

    @SerializedName("population")
    @Expose
    private String population;

    @SerializedName("countryCode")
    @Expose
    private String countryCode;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }
}
