package com.mcdonji.machikorostats.model;

public class IndexModel {
    private String welcomeMessage;
    private CardsHyperApi cardsHyperApi;

    public IndexModel(String welcomeMessage) {

        this.welcomeMessage = welcomeMessage;
        this.cardsHyperApi = new CardsHyperApi();
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }


    public CardsHyperApi getCardsHyperApi() {
        return cardsHyperApi;
    }

    public void setCardsHyperApi(CardsHyperApi cardsHyperApi) {
        this.cardsHyperApi = cardsHyperApi;
    }
}
