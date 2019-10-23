package com.mcdonji.machikorostats.model;

import com.mcdonji.machikorostats.domain.EstablishmentInDeck;

public class EstablishmentModel {
    private String name;
    private String count;
    private String enabler;
    private String cardColor;
    private String production;
    private String productionOnType;

    public EstablishmentModel(EstablishmentInDeck eid) {
        this.name = eid.getEstablishment().getName();
        this.count = "" + eid.getEstablishmentsNumber();
        this.enabler = "" + eid.getEstablishment().getEnabler();
        this.cardColor = "" + eid.getEstablishment().getCardColor();
        this.production = "" + eid.getEstablishment().getProduction();
        this.productionOnType = "" + eid.getEstablishment().getProductionOnType();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getEnabler() {
        return enabler;
    }

    public void setEnabler(String enabler) {
        this.enabler = enabler;
    }

    public String getCardColor() {
        return cardColor;
    }

    public void setCardColor(String cardColor) {
        this.cardColor = cardColor;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getProductionOnType() {
        return productionOnType;
    }

    public void setProductionOnType(String productionOnType) {
        this.productionOnType = productionOnType;
    }
}
