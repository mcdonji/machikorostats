package com.mcdonji.machikorostats.model;

public class EstablishmentsApi {
    public String url;
    public String description;

    public EstablishmentsApi() {
        url = "/establishments";
        description = "List of all establishments";
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
