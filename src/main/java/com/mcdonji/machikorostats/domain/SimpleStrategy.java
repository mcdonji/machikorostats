package com.mcdonji.machikorostats.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SimpleStrategy implements Strategy {
    public String getName() {
        return "SimpleStrategy";
    }

    public Establishment GetEstablishmentPreference(int money, EstablishmentDeck deck, Player player, Collection<Player> otherPlayers) {
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


    public boolean shouldActivateLandmark(int money, List<Landmark> landmarks) {
        return landmarkToActivate(money,  landmarks) != null;
    }

    public Landmark landmarkToActivate(int money, List<Landmark> landmarks) {
        for (Landmark landmark: landmarks) {
            if (landmark.isActive() == false && (money > landmark.getCost())) {
                return landmark;
            }
        }
        return null;
    }

    public int NumberOfDiceToRoll(Player player) {
        int canRoll = player.canRoll();
        if (canRoll == 2 ) {
            return 2;
        }
        return 1;
    }

    public boolean shouldReroll(DiceRoll roll) {
        return true;
    }

    public int NumberOfDiceToRollOnRerole(Player player) {
        // could be a different choice for different strategies
        int canRoll = player.canRoll();
        if (canRoll == 2){
            return 2;
        }
        return 1;
    }
}
