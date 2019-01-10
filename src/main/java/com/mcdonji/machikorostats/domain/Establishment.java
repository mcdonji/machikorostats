package com.mcdonji.machikorostats.domain;

import java.util.Collection;
import java.util.function.Function;

public class Establishment {
    private String name;
    private Function<Collection<Establishment>, Integer> production;
    private ProductionType productionType;
    private int[] activateOnRole;

    public Establishment(String name, Function<Collection<Establishment>, Integer> production, ProductionType productionType, int[] activateOnRole, Enabler wheat) {
        this.name = name;
        this.production = production;
        this.productionType = productionType;
        this.activateOnRole = activateOnRole;
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

    public ProductionType getProductionType() {
        return productionType;
    }
}
