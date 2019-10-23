package com.mcdonji.machikorostats.domain;

public class Establishments {
    public static Establishment WheatField = new Establishment(
            "Wheat Field",
            (establishments -> {return 1;}),
            ProductionOnType.AnyonesTurn,
            new int[]{1},
            Enabler.Farm,
            CardColor.Blue);
    public static Establishment Ranch = new Establishment(
            "Ranch",
            (establishments -> {return 1;}),
            ProductionOnType.AnyonesTurn,
            new int[]{2},
            Enabler.Ranch,
            CardColor.Blue);
    public static Establishment Bakery = new Establishment(
            "Bakery",
            (establishments -> {return 1;}),
            ProductionOnType.YourTurn,
            new int[]{2,3},
            Enabler.None,
            CardColor.Green);
    public static Establishment Cafe = new Establishment(
            "Cafe",
            (establishments -> {return 1;}),
            ProductionOnType.FromPlayerWhoRolledTheDice,
            new int[]{3},
            Enabler.None,
            CardColor.Red);
    public static Establishment ConvenienceStore = new Establishment(
            "Convenience Store",
            (establishments -> {return 3;}),
            ProductionOnType.YourTurn,
            new int[]{4},
            Enabler.None,
            CardColor.Green);
    public static Establishment Forest = new Establishment(
            "Forest",
            (establishments -> {return 1;}),
            ProductionOnType.AnyonesTurn,
            new int[]{5},
            Enabler.None,
            CardColor.Blue);
    public static Establishment TvStation = new Establishment(
            "TV Station",
            (establishments -> {return 5;}),
            ProductionOnType.YourTurnFromAnyChosenPlayer,
            new int[]{6},
            Enabler.Farm,
            CardColor.Purple);
    public static Establishment Stadium= new Establishment(
            "Stadium",
            (establishments -> {return 2;}),
            ProductionOnType.YourTurnFromAllPlayers,
            new int[]{6},
            Enabler.None,
            CardColor.Purple);
    public static Establishment BusinessCenter =  new Establishment(
            "Business Center",
            (establishments -> {return 0;}),
            ProductionOnType.YourTurnTradeEstablishment,
            new int[]{6},
            Enabler.None,
            CardColor.Purple
            );
    public static Establishment CheeseFactory = new Establishment(
            "Cheese Factory",
            (establishments -> {return  3 * (int)establishments.stream().filter(e->e.getEnabler()==Enabler.Ranch).count();}),
            ProductionOnType.YourTurn,
            new int[]{7},
            Enabler.None,
            CardColor.Green
            );
    public static Establishment FurnitureFactory = new Establishment(
            "Furniture Factory",
            (establishments -> {return  3 * (int)establishments.stream().filter(e->e.getEnabler()==Enabler.Mine).count();}),
            ProductionOnType.YourTurn,
            new int[]{8},
            Enabler.None,
            CardColor.Green);
    public static Establishment Mine = new Establishment(
            "Mine",
            (establishments -> {return 5;}),
            ProductionOnType.AnyonesTurn,
            new int[]{9},
            Enabler.None,
            CardColor.Blue);
    public static Establishment FamilyRestaurant = new Establishment(
            "Family Restaurant",
            (establishments -> {return 2;}),
            ProductionOnType.FromPlayerWhoRolledTheDice,
            new int[]{9,10},
            Enabler.None,
            CardColor.Red

            );
    public static Establishment AppleOrchard = new Establishment(
            "Apple Orchard",
            (establishments -> {return 3;}),
            ProductionOnType.AnyonesTurn,
            new int[]{10},
            Enabler.None,
            CardColor.Blue);
    public static Establishment FruitVegMarket = new Establishment(
            "Fruit Veg Market",
            (establishments -> {return  2 * (int)establishments.stream().filter(e->e.getEnabler()==Enabler.Farm).count();}),
            ProductionOnType.YourTurn,
            new int[]{11, 12},
            Enabler.Farm,
            CardColor.Green);
}
