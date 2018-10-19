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
}
