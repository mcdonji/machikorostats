package com.mcdonji.machikorostats.domain;

import java.util.ArrayList;

public class Landmark {
    private boolean isActive;
    private int cost;
    private String name;
    private String description;

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

    public static String TrainStationName = "TrainStation";
    public static String ShoppingMallName = "ShoppingMall";
    public static String AmusementParkName = "AmusementPark";
    public static String RadioTowerName = "RadioTower";

    public static Landmark canGetLandmark(int money, ArrayList<Landmark> landmarks, String radioTowerName) {
        Landmark radioTower = Landmark.get(radioTowerName, landmarks);
        if (!radioTower.isActive() && money >= radioTower.getCost()) {
            return radioTower;
        }
        return null;
    }


    public static Landmark getLandmarkInActivationOrder(int money, ArrayList<Landmark> landmarks, ArrayList<String> landMarkActivationOrder) {
        for (String landmarkName: landMarkActivationOrder) {
            Landmark choice = canGetLandmark(money, landmarks, landmarkName);
            if (choice != null) return choice;
        }
        return null;
    }


    public static Landmark get(String name, ArrayList<Landmark> landmarks) {
        return landmarks.stream().filter(landmark -> landmark.getName() == name).findFirst().get();
    }

    public static Landmark TrainStation() {
        Landmark trainstation = new Landmark();
        trainstation.cost = TrainStationCost;
        trainstation.name = TrainStationName;
        trainstation.description = "You may roll 1 or 2 dice";
        trainstation.isActive = false;
        return trainstation;
    }
    public static Landmark ShoppingMall() {
        Landmark shoppingmall = new Landmark();
        shoppingmall.cost = ShoppingMallCost;
        shoppingmall.name = ShoppingMallName;
        shoppingmall.description = "Each of your cafe's and bread's earn +1 coin";
        shoppingmall.isActive = false;
        return shoppingmall;
    }
    public static Landmark AmusementPark() {
        Landmark amusementpark = new Landmark();
        amusementpark.cost = AmusementParkCost;
        amusementpark.name = AmusementParkName;
        amusementpark.description = "If you roll doubles take another turn after this one";
        amusementpark.isActive = false;
        return amusementpark;
    }
    public static Landmark RadioTower() {
        Landmark radioTower = new Landmark();
        radioTower.cost = RadioTowerCost;
        radioTower.name = RadioTowerName;
        radioTower.description = "Once every turn you can choose to re-roll your dice";
        radioTower.isActive = false;
        return radioTower;
    }

}
