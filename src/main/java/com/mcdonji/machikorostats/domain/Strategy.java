package com.mcdonji.machikorostats.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Strategy {
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
        if (player.trainStation.isActive()) {
            return 2;
        }
        return 1;
    }

    public boolean shouldReroll(DiceRoll roll) {
        return true;
    }

    public int NumberOfDiceToRollOnRerole(Player player) {
        if (player.trainStation.isActive()){
            return 2;
        }
        return 1;
    }
}
