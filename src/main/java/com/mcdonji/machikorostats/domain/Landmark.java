package com.mcdonji.machikorostats.domain;

import java.util.ArrayList;
import java.util.List;

public class Landmark {
    private boolean isActive;
    private int cost;
    private String name;

    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    public static int TrainStationCost = 4;
    public static int ShoppingMallCost = 10;
    public static int AmusementParkCost = 16;
    public static int RadioTowerCost = 22;

    public static Landmark TrainStation() {
        Landmark trainstation = new Landmark();
        trainstation.cost = TrainStationCost;
        trainstation.name = "TrainStation";
        trainstation.isActive = false;
        return trainstation;
    }
    public static Landmark ShoppingMall() {
        Landmark shoppingmall = new Landmark();
        shoppingmall.cost = ShoppingMallCost;
        shoppingmall.name = "ShoppingMall";
        shoppingmall.isActive = false;
        return shoppingmall;
    }
    public static Landmark AmusementPark() {
        Landmark amusementpark = new Landmark();
        amusementpark.cost = AmusementParkCost;
        amusementpark.name = "AmusementPark";
        amusementpark.isActive = false;
        return amusementpark;
    }
    public static Landmark RadioTower() {
        Landmark radioTower = new Landmark();
        radioTower.cost = RadioTowerCost;
        radioTower.name = "RadioTower";
        radioTower.isActive = false;
        return radioTower;
    }
}
