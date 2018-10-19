package com.mcdonji.machikorostats.domain;

import java.util.Collection;

public class Establishment {
    private String name;
    private int production;
    private ProductionType productionType;
    private int[] activateOnRole;

    public Establishment(String name, int production, ProductionType productionType, int[] activateOnRole, Enabler wheat) {
        this.name = name;
        this.production = production;
        this.activateOnRole = activateOnRole;
    }

    public String getName() {
        return name;
    }

    public int getProduction(Collection<Establishment> establishments) {

        return production;
    }

    public int[] getActivateOnRole() {
        return activateOnRole;
    }

    public ProductionType getProductionType() {
        return productionType;
    }
}
