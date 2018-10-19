package com.mcdonji.machikorostats.domain;

import java.util.*;

import static sun.audio.AudioPlayer.player;

public class Player {

    private UUID id;
    private Random random;
    private int money;
    private Strategy strategy;
    private Collection<Player> otherPlayers = new ArrayList<Player>();
    private Collection<Establishment> establishments = new ArrayList<Establishment>();


    public Player(Random random, int money, Collection<Establishment> initialEstablishments) {
        this.random = random;
        this.money = money;
        establishments = initialEstablishments;
        this.id = UUID.randomUUID();
        strategy = new Strategy();
    }


    public void GetEstablishment(Establishment establishment) {
        establishments.add(establishment);
    }

    public void AddOtherPlayers(ArrayList<Player> allPlayers) {
        for (Player otherPlayer:allPlayers) {
            if (!id.equals(otherPlayer.getId())) {
                otherPlayers.add(otherPlayer);
            }
        }
    }

    public UUID getId() {
        return id;
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
        int revenue = revenueFromMyRoll(roll);
        money += revenue;
        for (Player otherPlayer: otherPlayers) {
            otherPlayer.revenueFromOtherPlayersRoll(roll);
        }
        Building building = shouldActivateBuilding();
        if (building !=null) {
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

    private int revenueFromMyRoll(int roll) {
        int revenue = 0;
        for (Establishment establishment: establishments) {
            if (establishment.getProductionType().equals(ProductionType.YourTurn)) {
                revenue += establishment.getProduction(establishments);
            }
        }
        return 0;
    }

    private int roll() {

        return random.nextInt(6);
    }
}
