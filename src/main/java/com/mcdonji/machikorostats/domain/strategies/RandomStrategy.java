package com.mcdonji.machikorostats.domain.strategies;

import com.mcdonji.machikorostats.domain.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomStrategy implements Strategy {
    private Random random;

    public RandomStrategy() {
        random = new Random();
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    public Establishment GetEstablishmentPreference(int money, EstablishmentDeck deck, Player player, Collection<Player> otherPlayers) {
        // 10% chance of doing nothing even if we can
        if (random.nextInt(10) == 1) {
            return null;
        }
        Establishment[] avaliableEstablishments = deck.AvaliableEstablishments().toArray(new Establishment[0]);
        List<Integer> range = (IntStream.range(0, avaliableEstablishments.length).boxed().collect(Collectors.toList()));
        Collections.shuffle(range);
        Integer[] shuffled = range.toArray(new Integer[range.size()]);
        for (int i = 0; i < range.size(); i++) {
            Establishment desired = avaliableEstablishments[shuffled[i]];
            if (deck.CostOf(desired) <= money) {
                return desired;
            }
        }
        return null;
    }

    public Landmark landmarkToActivate(int money, List<Landmark> landmarks) {
        // 10% chance of doing nothing even if we can
        if (random.nextInt(10) == 1) {
            return null;
        }
        Collections.shuffle(landmarks);
        for (Landmark landmark : landmarks) {
            if (landmark.isActive() == false && (money > landmark.getCost())) {
                return landmark;
            }
        }
        return null;
    }

    @Override
    public Player ChoosePlayerToTakeFrom(Player player, Collection<Player> otherPlayers) {
        ArrayList<Player> op = new ArrayList<Player>(otherPlayers);
        Collections.shuffle(op);
        return op.iterator().next();
    }

    @Override
    public EstablishmentTrade ChoosePlayerAndEstablishmentToTakeAndGive(Player player, Collection<Player> otherPlayers) {
        ArrayList<Player> ops = new ArrayList<Player>(otherPlayers);
        Collections.shuffle(ops);
        Player otherPlayer =  ops.iterator().next();
        ArrayList<Establishment> otherPlayerNotPurpleEstablishments = new ArrayList<Establishment>(otherPlayer.getNotPurpleEstablishments());
        Collections.shuffle(otherPlayerNotPurpleEstablishments);
        ArrayList<Establishment> playerNotPurpleEstablishments = new ArrayList<Establishment>(player.getNotPurpleEstablishments());
        Collections.shuffle(playerNotPurpleEstablishments);
        return new EstablishmentTrade(otherPlayer, otherPlayerNotPurpleEstablishments.iterator().next(), playerNotPurpleEstablishments.iterator().next());
    }

    public int NumberOfDiceToRoll(Player player) {
        int canRoll = player.canRoll();
        if (canRoll == 2) {
            return (random.nextInt(1) == 1) ? 1 : 2;
        }
        return 1;
    }

    public boolean shouldReroll(DiceRoll roll) {
        return (random.nextInt(1) == 1);
    }

    public int NumberOfDiceToRollOnRerole(Player player) {
        // could be a different choice for different strategiesBred
        return NumberOfDiceToRoll(player);
    }
}
