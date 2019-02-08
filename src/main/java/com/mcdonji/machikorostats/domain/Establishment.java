package com.mcdonji.machikorostats.domain;

import java.util.Collection;
import java.util.function.Function;

public class Establishment {
    private String name;
    private Function<Collection<Establishment>, Integer> production;
    private ProductionOnType productionOnType;
    private int[] activateOnRole;
    private Enabler enabler;

    public Establishment(String name, Function<Collection<Establishment>, Integer> production, ProductionOnType productionOnType, int[] activateOnRole, Enabler enabler) {
        this.name = name;
        this.production = production;
        this.productionOnType = productionOnType;
        this.activateOnRole = activateOnRole;
        this.enabler = enabler;
    }

    public String getName() {
        return name;
    }

    public int getProduction(Collection<Establishment> establishments) {
        return production.apply(establishments);
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

    public void setEnabler(Enabler enabler) {
        this.enabler = enabler;
    }
}
