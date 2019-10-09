package com.mcdonji.machikorostats.domain;

import org.springframework.util.StopWatch;

import java.util.*;

public class Game {
    private Date startTime;
    private Date endTime;
    private EstablishmentDeck deck;
    private ArrayList<Player> players;
    private Random random;

    public int getRoundsToComplete() {
        return roundsToComplete;
    }

    private int roundsToComplete = 0;

    public Game(List<Strategy> strategies) {
        deck = EstablishmentDeck.CreateDeck();
        players = new ArrayList<Player>(strategies.size());
        random = new Random();

        for (int i = 0; i < strategies.size(); i++) {
            ArrayList<Establishment> initEstablishments = new ArrayList<>();
            initEstablishments.add(deck.Take(Establishments.WheatField));
            initEstablishments.add(deck.Take(Establishments.Bakery));
            Player player = new Player(i, random, 3, initEstablishments, strategies.get(i));
            players.add(player);
        }

        for (Player player : players) {
            player.addOtherPlayers(players);
        }
    }

    public GameResult play() {
        StopWatch stopWatch = new StopWatch();
        this.startTime = new Date();
        stopWatch.start();
        while (isGameComplete()) {
            players.forEach(player -> deck = player.Move(deck));
            roundsToComplete++;
        }
        stopWatch.stop();
        GameResult
        this.endTime = new Date();
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
