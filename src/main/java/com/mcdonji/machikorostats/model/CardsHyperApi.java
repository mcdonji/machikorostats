package com.mcdonji.machikorostats.model;

public class CardsHyperApi {
    public String url;
    public String description;

    public CardsHyperApi() {
        url = "/cards";
        description = "List of All possible cards";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
