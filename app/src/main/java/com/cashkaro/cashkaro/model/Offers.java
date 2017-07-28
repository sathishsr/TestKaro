package com.cashkaro.cashkaro.model;

/**
 * Created by SathishSr
 */

public class Offers  {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String title;
    private String description;
    private String extraOffer;

    public String getExtraOffer() {
        return extraOffer;
    }

    public void setExtraOffer(String extraOffer) {
        this.extraOffer = extraOffer;
    }
}
