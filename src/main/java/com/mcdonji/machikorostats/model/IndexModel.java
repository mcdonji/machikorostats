package com.mcdonji.machikorostats.model;

public class IndexModel {
    private String welcomeMessage;
    private EstablishmentsApi establishmentsApi;
    private GameApi gameApi;

    public IndexModel(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
        this.establishmentsApi = new EstablishmentsApi();
        this.gameApi = new GameApi();
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }


    public EstablishmentsApi getEstablishmentsApi() {
        return establishmentsApi;
    }

    public void setEstablishmentsApi(EstablishmentsApi establishmentsApi) {
        this.establishmentsApi = establishmentsApi;
    }

    public GameApi getGameApi() {
        return gameApi;
    }

    public void setGameApi(GameApi gameApi) {
        this.gameApi = gameApi;
    }
}
