package com.mcdonji.machikorostats.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

public class Game {
    private Date startTime;
    private Date endTime;
    private EstablishmentDeck deck;
    private ArrayList<Player> players;
    private Random random;
    public Game(int numberOfPlayers) {
        deck = EstablishmentDeck.CreateDeck();
        players = new ArrayList<Player>(numberOfPlayers);
        random = new Random();

        for (int i = 0; i < numberOfPlayers; i++) {
            ArrayList<Establishment> initEstablishments = new ArrayList<>();
            initEstablishments.add(deck.Take(Establishments.WheatField));
            initEstablishments.add(deck.Take(Establishments.Bakery));
            Player player = new Player(i, random, 3, initEstablishments);
            players.add(player);
        }

        for (Player player : players) {
            player.addOtherPlayers(players);
        }
    }

    public void play() {
        this.startTime = new Date();
        while (isGameComplete()) {
            players.forEach(player -> deck = player.Move(deck));
        }
    }

    private boolean isGameComplete()
    {
        return players.stream().filter(player -> player.HasWon()).count() > 0;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
}
