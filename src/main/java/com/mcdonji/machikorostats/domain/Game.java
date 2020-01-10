package com.mcdonji.machikorostats.domain;

import org.springframework.util.StopWatch;

import java.util.*;
import java.util.stream.Collectors;

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


    public Game(Strategy... strategies) {
        Game(Arrays.asList(strategies));
    }

    protected void Game(List<Strategy> strategies) {
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

    public Player getFirstPlayer() {
        return players.stream().filter(p -> p.getPlayerNumber() == 1).findFirst().get();
    }

    public GameResult play() {
        StopWatch stopWatch = new StopWatch();
        this.startTime = new Date();
        stopWatch.start();
        GameResult gameResult = new GameResult();
        while (!isGameComplete()) {
            players.forEach(p->deck = p.move(deck));
            gameResult.AddTick(players.stream().map(Player::compactRepresentation).collect(Collectors.joining(" ")));
            roundsToComplete++;
        }
        stopWatch.stop();
        this.endTime = new Date();
        gameResult.setSeconds(stopWatch.getTotalTimeMillis());
        for (Player player: players ) {
            if (player.hasWon()) {
                gameResult.Winner = player;
            }
        }


        return gameResult;
    }


    private boolean isGameComplete()
    {
        for (Player player: players ) {
            if (player.hasWon()) {
                return true;
            }
        }
        return false;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
}
