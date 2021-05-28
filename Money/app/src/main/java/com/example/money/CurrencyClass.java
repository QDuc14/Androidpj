package com.example.money;

public class CurrencyClass {
    private String title;
    private String link;
    private double description;

    public CurrencyClass(String title, String link, double description) {
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public CurrencyClass(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double getDescription() {
        return description;
    }

    public void setDescription(double description) {
        this.description = description;
    }
}
