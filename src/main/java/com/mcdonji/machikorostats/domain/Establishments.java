package com.mcdonji.machikorostats.domain;

public class Establishments {
    public static Establishment WheatField = new Establishment(
            "Wheat Field",
            ((landmarks, establishments) -> {return 1;}),
            ProductionOnType.AnyonesTurn,
            new int[]{1},
            Enabler.Farm,
            CardColor.Blue);
    public static Establishment Ranch = new Establishment(
            "Ranch",
            ((landmarks, establishments) -> {return 1;}),
            ProductionOnType.AnyonesTurn,
            new int[]{2},
            Enabler.Ranch,
            CardColor.Blue);
    public static Establishment Bakery = new Establishment(
            "Bakery",
            ((landmarks, establishments) -> {return Landmark.get(Landmark.ShoppingMallName, landmarks).isActive()?  2 : 1;}),
            ProductionOnType.YourTurn,
            new int[]{2,3},
            Enabler.Bread,
            CardColor.Green);
    public static Establishment Cafe = new Establishment(
            "Cafe",
            ((landmarks, establishments) -> {return Landmark.get(Landmark.ShoppingMallName, landmarks).isActive()?  2 : 1;}),
            ProductionOnType.FromPlayerWhoRolledTheDice,
            new int[]{3},
            Enabler.Cafe,
            CardColor.Red);
    public static Establishment ConvenienceStore = new Establishment(
            "Convenience Store",
            ((landmarks, establishments) -> {return 3;}),
            ProductionOnType.YourTurn,
            new int[]{4},
            Enabler.Bread,
            CardColor.Green);
    public static Establishment Forest = new Establishment(
            "Forest",
            ((landmarks, establishments) -> {return 1;}),
            ProductionOnType.AnyonesTurn,
            new int[]{5},
            Enabler.Mine,
            CardColor.Blue);
    public static Establishment TvStation = new Establishment(
            "TV Station",
            ((landmarks, establishments) -> {return 5;}),
            ProductionOnType.YourTurnFromAnyChosenPlayer,
            new int[]{6},
            Enabler.Station,
            CardColor.Purple);
    public static Establishment Stadium= new Establishment(
            "Stadium",
            ((landmarks, establishments) -> {return 2;}),
            ProductionOnType.YourTurnFromAllPlayers,
            new int[]{6},
            Enabler.Station,
            CardColor.Purple);
    public static Establishment BusinessCenter =  new Establishment(
            "Business Center",
            ((landmarks, establishments) -> {return 0;}),
            ProductionOnType.YourTurnTradeEstablishment,
            new int[]{6},
            Enabler.Station,
            CardColor.Purple
            );
    public static Establishment CheeseFactory = new Establishment(
            "Cheese Factory",
            ((landmarks, establishments) -> {return  3 * (int)establishments.stream().filter(e->e.getEnabler()==Enabler.Ranch).count();}),
            ProductionOnType.YourTurn,
            new int[]{7},
            Enabler.Factory,
            CardColor.Green
            );
    public static Establishment FurnitureFactory = new Establishment(
            "Furniture Factory",
            ((landmarks, establishments) -> {return  3 * (int)establishments.stream().filter(e->e.getEnabler()==Enabler.Mine).count();}),
            ProductionOnType.YourTurn,
            new int[]{8},
            Enabler.Factory,
            CardColor.Green);
    public static Establishment Mine = new Establishment(
            "Mine",
            ((landmarks, establishments) -> {return 5;}),
            ProductionOnType.AnyonesTurn,
            new int[]{9},
            Enabler.Mine,
            CardColor.Blue);
    public static Establishment FamilyRestaurant = new Establishment(
            "Family Restaurant",
            ((landmarks, establishments) -> {return Landmark.get(Landmark.ShoppingMallName, landmarks).isActive()?  3 : 2;}),
            ProductionOnType.FromPlayerWhoRolledTheDice,
            new int[]{9,10},
            Enabler.Cafe,
            CardColor.Red
            );
    public static Establishment AppleOrchard = new Establishment(
            "Apple Orchard",
            ((landmarks, establishments) -> {return 3;}),
            ProductionOnType.AnyonesTurn,
            new int[]{10},
            Enabler.Farm,
            CardColor.Blue);
    public static Establishment FruitVegMarket = new Establishment(
            "Fruit Veg Market",
            ((landmarks, establishments) -> {return  2 * (int)establishments.stream().filter(e->e.getEnabler()==Enabler.Farm).count();}),
            ProductionOnType.YourTurn,
            new int[]{11, 12},
            Enabler.Market,
            CardColor.Green);
}
