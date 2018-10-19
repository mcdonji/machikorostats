package com.mcdonji.machikorostats.domain;

public class Establishments {
    public static Establishment WheatField = new Establishment("Wheat Field",1, ProductionType.AnyonesTurn, new int[]{1}, Enabler.Wheat);
    public static Establishment Ranch = new Establishment("Ranch", 1, ProductionType.YourTurn, new int[]{2,3}, Enabler.Wheat);
    public static Establishment Bakery = new Establishment("Bakery", 1, ProductionType.AnyonesTurn, new int[]{1}, Enabler.Wheat);
    public static Establishment Cafe = new Establishment("Cafe",  1, ProductionType.AnyonesTurn, new int[]{1}, Enabler.Wheat);
    public static Establishment ConvenienceStore = new Establishment("Convenience Store",  1, ProductionType.AnyonesTurn, new int[]{1}, Enabler.Wheat);
    public static Establishment FruitVegMarket = new Establishment("Fruit Veg Market", 1, ProductionType.AnyonesTurn, new int[]{1}, Enabler.Wheat);
    public static Establishment Forest = new Establishment("Forest", 3, ProductionType.AnyonesTurn, new int[]{1}, Enabler.Wheat);
    public static Establishment FurnitureFactory = new Establishment("Furniture Factory", 1, ProductionType.AnyonesTurn, new int[]{1}, Enabler.Wheat);
    public static Establishment AppleOrchard = new Establishment("Apple Orchard", 1, ProductionType.AnyonesTurn, new int[]{1}, Enabler.Wheat);
    public static Establishment FamilyRestaurant = new Establishment("Family Restaurant", 1, ProductionType.AnyonesTurn, new int[]{1}, Enabler.Wheat);
    public static Establishment CheeseFactory = new Establishment("Cheese Factory", 1, ProductionType.AnyonesTurn, new int[]{1}, Enabler.Wheat);
    public static Establishment Stadium= new Establishment("Stadium", 6, ProductionType.AnyonesTurn, new int[]{1}, Enabler.Wheat);
    public static Establishment Mine = new Establishment("Mine", 6, ProductionType.AnyonesTurn, new int[]{1}, Enabler.Wheat);
    public static Establishment TvStation = new Establishment("TV Station", 1, ProductionType.AnyonesTurn, new int[]{1}, Enabler.Wheat);
    public static Establishment BusinessCenter =  new Establishment("Business Center",1,ProductionType.AnyonesTurn, new int[]{1}, Enabler.Wheat);
}
