package com.mcdonji.machikorostats.domain;

import java.util.*;


public class Player {

    private UUID id;
    private int playerNumber;
    private String name;
    private Random random;
    private int money;
    private Strategy strategy;
    private Collection<Player> otherPlayers = new ArrayList<Player>();
    private Collection<Establishment> establishments = new ArrayList<Establishment>();
    private Collection<Building> buildings = new ArrayList<Building>();


    public Player(int playerNumber, String name, Random random, int money, Collection<Establishment> initialEstablishments)    {
    	this(UUID.randomUUID(), playerNumber, name, random, money, initialEstablishments);
    }

    public Player(int playerNumber, Random random, int money, Collection<Establishment> initialEstablishments)    {
	    this(UUID.randomUUID(),playerNumber, "Player$(playerNumber)", random, money, initialEstablishments);
    }

    public Player(UUID id, int playerNumber,  String name, Random random, int money, Collection<Establishment> initialEstablishments)
    {
        this.id = id;
        this.playerNumber = playerNumber;
	    this.name = name;
        this.random = random;
        this.money = money;
        establishments = initialEstablishments;
        strategy = new Strategy();
    }


    public void getEstablishment(Establishment establishment) {
        establishments.add(establishment);
    }

    public void addOtherPlayers(ArrayList<Player> allPlayers) {
        for (Player otherPlayer:allPlayers) {
            if (!id.equals(otherPlayer.getId())) {
                otherPlayers.add(otherPlayer);
            }
        }
    }

    public int otherPlayerCount() 
    {
	return otherPlayers.size();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public EstablishmentDeck Move(EstablishmentDeck deck) {
        int roll = roll();
        int revenueFromMe = revenueFromMyRoll(roll);
        money += revenueFromMe;
        int revenueFromOthers = 0;
        for (Player otherPlayer: otherPlayers) {
            revenueFromOthers += otherPlayer.revenueFromOtherPlayersRoll(roll);
        }

        money += revenueFromOthers;

        Building building = shouldActivateBuilding();
        if (building != null) {
            activateBuilding(building);
        } else {
            Establishment desired = strategy.GetEstablishmentPreference(money, deck, this, otherPlayers);
            establishments.add(deck.Take(desired));
        }
        return deck;
    }

    private void activateBuilding(Building building) {

    }

    private Building shouldActivateBuilding() {
        return null;
    }

    private void revenueFromOtherPlayersRoll(int roll) {
        int revenue = 0;
        for (Establishment establishment: establishments) {

        }
        money += revenue;
    }

    public int revenueFromMyRoll(int roll) {
        int revenue = 0;
        for (Establishment establishment: establishments) {
            if (Arrays.stream(establishment.getActivateOnRole()).anyMatch(ao->ao ==roll)) {
                if (establishment.getProductionType().equals(ProductionType.YourTurn) ||
                        establishment.getProductionType().equals(ProductionType.AnyonesTurn)) {
                    revenue += establishment.getProduction(establishments);
                }
            }
        }
        return revenue;
    }

    private int roll() {
        return random.nextInt(6);
    }

    public boolean HasWon() {
        return false;
    }
}
