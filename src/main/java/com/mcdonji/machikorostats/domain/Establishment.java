package com.mcdonji.machikorostats.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Establishment {
    private String name;
    private BiFunction<ArrayList<Landmark>, ArrayList<Establishment>, Integer> production;
    private ProductionOnType productionOnType;
    private int[] activateOnRole;
    private Enabler enabler;
    private CardColor cardColor;

    public Establishment(String name, BiFunction<ArrayList<Landmark>, ArrayList<Establishment>, Integer> production, ProductionOnType productionOnType, int[] activateOnRole, Enabler enabler, CardColor cardColor) {
        this.name = name;
        this.production = production;
        this.productionOnType = productionOnType;
        this.activateOnRole = activateOnRole;
        this.enabler = enabler;
        this.cardColor = cardColor;
    }

    public String getName() {
        return name;
    }

    public int getProduction() {
        return production.apply(new ArrayList<Landmark>(), new ArrayList<Establishment>());
    }
    public int getProduction(ArrayList<Landmark> landmarks, ArrayList<Establishment> establishments) {
        return production.apply(landmarks, establishments);
    }

    public int[] getActivateOnRole() {
        return activateOnRole;
    }

    public ProductionOnType getProductionOnType() {
        return productionOnType;
    }

    public Enabler getEnabler() {
        return enabler;
    }
    public CardColor getCardColor() {
        return cardColor;
    }

    public void setEnabler(Enabler enabler) {
        this.enabler = enabler;
    }

    public boolean isRed() {
        return cardColor == CardColor.Red;
    }

    public boolean isGreenOrBlue() {
        return cardColor == CardColor.Green || cardColor == CardColor.Blue;
    }

    public boolean isPurple() {
        return cardColor == CardColor.Purple;
    }
    public boolean isNotPurple() {
        return cardColor != CardColor.Purple;
    }

    public boolean isTradeEstablishment() {
        return productionOnType == ProductionOnType.YourTurnTradeEstablishment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Establishment that = (Establishment) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
