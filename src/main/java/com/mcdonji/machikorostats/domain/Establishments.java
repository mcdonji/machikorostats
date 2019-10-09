package com.mcdonji.machikorostats.domain;

public class Establishments {
    public static Establishment WheatField = new Establishment(
            "Wheat Field",
            (establishments -> {return 1;}),
            ProductionOnType.YourTurn,
            new int[]{1},
            Enabler.Farm);
    public static Establishment Ranch = new Establishment(
            "Ranch",
            (establishments -> {return 1;}),
            ProductionOnType.YourTurn,
            new int[]{2},
            Enabler.Ranch);
    public static Establishment Bakery = new Establishment(
            "Bakery",
            (establishments -> {return 1;}),
            ProductionOnType.YourTurn,
            new int[]{2,3},
            Enabler.None);
    public static Establishment Cafe = new Establishment(
            "Cafe",
            (establishments -> {return 1;}),
            ProductionOnType.YourTurn,
            new int[]{3},
            Enabler.None);
    public static Establishment ConvenienceStore = new Establishment(
            "Convenience Store",
            (establishments -> {return 1;}),
            ProductionOnType.YourTurn,
            new int[]{4},
            Enabler.None);
    public static Establishment FruitVegMarket = new Establishment(
            "Fruit Veg Market",
            (establishments -> {return  2 * (int)establishments.stream().filter(e->e.getEnabler()==Enabler.Farm).count();}),
            ProductionOnType.YourTurn,
            new int[]{4},
            Enabler.Farm );
    public static Establishment Forest = new Establishment("Forest", (establishments -> {return 3;}), ProductionOnType.AnyonesTurn, new int[]{5}, Enabler.None);
    public static Establishment FurnitureFactory = new Establishment("Furniture Factory", (establishments -> {return 1;}), ProductionOnType.AnyonesTurn, new int[]{6}, Enabler.None);
    public static Establishment AppleOrchard = new Establishment("Apple Orchard", (establishments -> {return 1;}), ProductionOnType.AnyonesTurn, new int[]{6}, Enabler.None);
    public static Establishment FamilyRestaurant = new Establishment("Family Restaurant", (establishments -> {return 1;}), ProductionOnType.AnyonesTurn, new int[]{6}, Enabler.None);
    public static Establishment CheeseFactory = new Establishment(
            "Cheese Factory",
            (establishments -> {return  3 * (int)establishments.stream().filter(e->e.getEnabler()==Enabler.Ranch).count();}),
            ProductionOnType.YourTurn,
            new int[]{7},
            Enabler.None);
    public static Establishment Stadium= new Establishment(
            "Stadium",
            (establishments -> {return 1;}),
            ProductionOnType.AnyonesTurn,
            new int[]{1},
            Enabler.None);
    public static Establishment Mine = new Establishment(
            "Mine",
            (establishments -> {return 6;}),
            ProductionOnType.AnyonesTurn,
            new int[]{1},
            Enabler.None);
    public static Establishment TvStation = new Establishment(
            "TV Station", (establishments -> {return 1;}), ProductionOnType.AnyonesTurn, new int[]{1}, Enabler.Farm);
    public static Establishment BusinessCenter =  new Establishment("Business Center",(establishments -> {return 1;}), ProductionOnType.AnyonesTurn, new int[]{1}, Enabler.Farm);
}
