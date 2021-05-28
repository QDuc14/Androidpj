package com.example.baitap_3;

public class DiaChiURL {
    private String dcURL, geo;

    public DiaChiURL(String dcURL, String geo){
        this.dcURL= dcURL;
        this.geo = geo;
    }

    public String getTenURL(){
        return dcURL;
    }

    public String getGEO() { return this.geo; }

    public void setTenURL(String dcURL){
        this.dcURL=dcURL;
    }
}
